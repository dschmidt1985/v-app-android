package app.v.verbundstudium.com.verbundstudiumapp

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import app.v.verbundstudium.com.verbundstudiumapp.calendar.CalendarActivity
import app.v.verbundstudium.com.verbundstudiumapp.di.DaggerMainComponent
import app.v.verbundstudium.com.verbundstudiumapp.exams.ExamsActivity
import app.v.verbundstudium.com.verbundstudiumapp.lessons.LessonsActivity
import app.v.verbundstudium.com.verbundstudiumapp.login.LoginActivity
import app.v.verbundstudium.com.verbundstudiumapp.mensa.MensaActivity
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModel.MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainComponent.create().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        initButtons()
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            finish()
        } else {
            subscribeToPush()
        }
    }

    fun subscribeToPush() {
        FirebaseMessaging.getInstance().subscribeToTopic("calendar_events")
    }

    private fun initButtons() {
        settings_btn.setOnClickListener { notImplementYet() }
        calendar_btn.setOnClickListener { showCalendar() }
        mensa_btn.setOnClickListener { startActivity(Intent(this, MensaActivity::class.java)) }
        sprechstunden_btn.setOnClickListener { notImplementYet() }
        stundenplan_btn.setOnClickListener { startActivity(Intent(this, LessonsActivity::class.java)) }
        ilias_btn.setOnClickListener { openUrl("http://ec2-18-188-33-124.us-east-2.compute.amazonaws.com/") }
        evaluation_btn.setOnClickListener { notImplementYet() }
        campus_nav_btn.setOnClickListener { notImplementYet() }
        bib_btn.setOnClickListener { openUrl("https://www.th-koeln.de/hochschulbibliothek/hochschulbibliothek_3862.php") }
        pruefungsplan_btn.setOnClickListener {  startActivity(Intent(this, ExamsActivity::class.java))  }
        messages_btn.setOnClickListener { notImplementYet() }
        psso_btn.setOnClickListener { openUrl("http://serviceinfo.campus-it.th-koeln.de/psso/") }
        th_koeln_btn.setOnClickListener { openUrl("https://www.th-koeln.de/") }
        fh_dortmund_btn.setOnClickListener { openUrl("https://www.fh-dortmund.de/de/index.php") }
    }

    private fun showCalendar() {
        startActivity(Intent(this, CalendarActivity::class.java))
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun notImplementYet() {
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.coming_soon_message), Snackbar.LENGTH_SHORT).show()
    }

}
