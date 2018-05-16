package app.v.verbundstudium.com.verbundstudiumapp.mensa

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.v.verbundstudium.com.verbundstudiumapp.R
import app.v.verbundstudium.com.verbundstudiumapp.common.BaseFragment
import app.v.verbundstudium.com.verbundstudiumapp.di.DaggerMainComponent
import kotlinx.android.synthetic.main.fragment_mensa.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class MensaFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MensaViewModel.MensaViewModelFactory

    private lateinit var viewModel: MensaViewModel

    private var date = DateTime()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.create().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MensaViewModel::class.java)
        //TODO should come from outside
        val typeOrdinal = arguments?.getInt(MENSA_TYPE_KEY, MensaType.DORTMUND.ordinal)
                ?: MensaType.DORTMUND.ordinal
        val mensaType = MensaType.values()[typeOrdinal]
        viewModel.setMensaType(mensaType)

        viewModel.dateLiveData.observe(this, Observer { date ->
            date?.let {
                date_button.text = it.toString(DateTimeFormat.fullDate())
                if (!this.date.isEqual(it)) {
                    viewModel.loadData()
                }
                this.date = it
            }
        })

        viewModel.viewState.observe(this, Observer { mensaViewState ->
            when (mensaViewState) {
                is MensaViewState.Init -> {
                    showProgress(false)
                }
                is MensaViewState.Progress -> {
                    showProgress(true)
                }
                is MensaViewState.Error -> {
                    showProgress(false)
                    Snackbar.make(activity!!.findViewById(android.R.id.content), mensaViewState.error.message, Snackbar.LENGTH_SHORT).show()

                }
                is MensaViewState.Success -> {
                    (mensa_recycler_view.adapter as GerichtAdapter).refreshDishes(mensaViewState.gerichte)
                    if (mensaViewState.gerichte.isEmpty()) {
                        mensa_recycler_view.visibility = View.GONE
                        mensa_closed_msg.visibility = View.VISIBLE
                    } else {
                        mensa_closed_msg.visibility = View.GONE
                        mensa_recycler_view.visibility = View.VISIBLE
                    }
                    showProgress(false)

                }
            }
        })
        viewModel.loadData()

    }

    private fun showProgress(show: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            mensa_area.visibility = if (show) View.GONE else View.VISIBLE
            mensa_area.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            mensa_area.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            mensa_progress.visibility = if (show) View.VISIBLE else View.GONE
            mensa_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            mensa_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mensa_progress.visibility = if (show) View.VISIBLE else View.GONE
            mensa_area.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mensa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewManager = LinearLayoutManager(context)
        val viewAdapter = GerichtAdapter(emptyList<Gericht>().toMutableList())
        mensa_recycler_view.apply {
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        calendar_img.setOnClickListener {
            changeDate()
        }
        date_button.setOnClickListener {
            changeDate()
        }

    }

    fun changeDate() {
        DatePickerDialog(context, { view, year, month, dayOfMonth ->
            viewModel.dateLiveData.value = DateTime(year, month + 1, dayOfMonth, 0, 0)
        }, date.year, date.monthOfYear - 1, date.dayOfMonth).show()

    }

    companion object {

        private val MENSA_TYPE_KEY = "MENSA_TYPE_KEY"

        fun newInstance(mensaType: MensaType): MensaFragment {
            return MensaFragment().apply {
                arguments = Bundle().apply { putInt(MENSA_TYPE_KEY, mensaType.ordinal) }
            }
        }

    }

}