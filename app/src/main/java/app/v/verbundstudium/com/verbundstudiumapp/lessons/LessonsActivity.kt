package app.v.verbundstudium.com.verbundstudiumapp.lessons

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
import app.v.verbundstudium.com.verbundstudiumapp.exams.ExamsViewState
import kotlinx.android.synthetic.main.activity_lessons.*
import javax.inject.Inject

class LessonsActivity: AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: LessonsViewModel.LessonsViewModelFactory
    private lateinit var mViewModel: LessonsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        DaggerMainComponent.create().inject(this)

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(LessonsViewModel::class.java)

        mViewModel.viewState.observe(this, Observer { scheduleViewState ->
            when (scheduleViewState) {
                is LessonsViewState.Init -> {
                    showProgress(false)
                }
                is LessonsViewState.Progress -> {
                    showProgress(true)
                }
                is LessonsViewState.Error -> {
                    showProgress(false)
                    Snackbar.make(findViewById(android.R.id.content), scheduleViewState.error.message, Snackbar.LENGTH_SHORT).show()
                }
                is LessonsViewState.Success -> {
                    (schedule_recycler_view.adapter as LessonsAdapter).refershLessons(scheduleViewState.lessons)
                    showProgress(false)
                }
            }
        })

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = LessonsAdapter(Lessons("empty", emptyList()))

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