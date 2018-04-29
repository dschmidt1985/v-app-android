package app.v.verbundstudium.com.verbundstudiumapp.schedule

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import app.v.verbundstudium.com.verbundstudiumapp.R
import app.v.verbundstudium.com.verbundstudiumapp.di.DaggerMainComponent
import kotlinx.android.synthetic.main.activity_schedule.*
import javax.inject.Inject

class ScheduleActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ScheduleViewModel.ScheduleViewModelFactory
    private lateinit var viewModel: ScheduleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        DaggerMainComponent.create().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ScheduleViewModel::class.java)

        viewModel.viewState.observe(this, Observer {scheduleViewState ->
            when (scheduleViewState) {
                is ScheduleViewState.Init -> {
                    showProgress(false)
                }
                is ScheduleViewState.Progress -> {
                    showProgress(true)
                }
                is ScheduleViewState.Error -> {
                    showProgress(false)
                    Snackbar.make(findViewById(android.R.id.content), scheduleViewState.error.message, Snackbar.LENGTH_SHORT).show()
                }
                is ScheduleViewState.Success -> {
                    (schedule_recycler_view.adapter as ScheduleAdapter).refershSchedule(scheduleViewState.schedule)
                    showProgress(false)
                }
            }
        })

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = ScheduleAdapter(Schedule("empty", emptyList()))

        schedule_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewModel.loadData()


    }



    private fun showProgress(show: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            schedule_recycler_view.visibility = if (show) View.GONE else View.VISIBLE
            schedule_recycler_view.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            schedule_recycler_view.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            schedule_progress.visibility = if (show) View.VISIBLE else View.GONE
            schedule_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            schedule_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            schedule_progress.visibility = if (show) View.VISIBLE else View.GONE
            schedule_recycler_view.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

}