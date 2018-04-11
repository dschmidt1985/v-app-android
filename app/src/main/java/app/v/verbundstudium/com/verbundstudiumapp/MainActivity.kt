package app.v.verbundstudium.com.verbundstudiumapp

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import app.v.verbundstudium.com.verbundstudiumapp.di.DaggerMainComponent
import app.v.verbundstudium.com.verbundstudiumapp.login.LoginActivity
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
        }
    }

    private fun initButtons() {
        settings_btn.setOnClickListener { notImplementYet() }
        calendar_btn.setOnClickListener { notImplementYet() }
        mensa_btn.setOnClickListener { notImplementYet() }
        sprechstunden_btn.setOnClickListener { notImplementYet() }
        stundenplan_btn.setOnClickListener { notImplementYet() }
        ilias_btn.setOnClickListener { notImplementYet() }
        evaluation_btn.setOnClickListener { notImplementYet() }
        campus_nav_btn.setOnClickListener { notImplementYet() }
        bib_btn.setOnClickListener { notImplementYet() }
        pruefungsplan_btn.setOnClickListener { notImplementYet() }
        messages_btn.setOnClickListener { notImplementYet() }
        psso_btn.setOnClickListener { openUrl("http://serviceinfo.campus-it.th-koeln.de/psso/") }
        th_koeln_btn.setOnClickListener { openUrl("https://www.th-koeln.de/") }
        fh_dortmund_btn.setOnClickListener { openUrl("https://www.fh-dortmund.de/de/index.php") }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun notImplementYet() {
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.coming_soon_message), Snackbar.LENGTH_SHORT).show()
    }

}
