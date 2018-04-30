package app.v.verbundstudium.com.verbundstudiumapp.calendar

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
import app.v.verbundstudium.com.verbundstudiumapp.lessons.CalendarAdapter
import kotlinx.android.synthetic.main.activity_exams.*
import javax.inject.Inject

class CalendarActivity: AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: CalendarViewModel.CalendarViewModelFactory
    private lateinit var mViewModel: CalendarViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendars)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        DaggerMainComponent.create().inject(this)

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CalendarViewModel::class.java)

        mViewModel.viewState.observe(this, Observer { calendarViewState ->
            when (calendarViewState) {
                is CalendarViewState.Init -> {
                    showProgress(false)
                }
                is CalendarViewState.Progress -> {
                    showProgress(true)
                }
                is CalendarViewState.Error -> {
                    showProgress(false)
                    Snackbar.make(findViewById(android.R.id.content), calendarViewState.error.message, Snackbar.LENGTH_SHORT).show()
                }
                is CalendarViewState.Success -> {
                    (schedule_recycler_view.adapter as CalendarAdapter).refreshCalendars(calendarViewState.calendars)
                    showProgress(false)
                }
            }
        })

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = CalendarAdapter(emptyList())

        schedule_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        mViewModel.loadData()
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