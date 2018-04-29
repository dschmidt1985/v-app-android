package app.v.verbundstudium.com.verbundstudiumapp.mensa

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.v.verbundstudium.com.verbundstudiumapp.R
import kotlinx.android.synthetic.main.activity_mensa.*
import kotlinx.android.synthetic.main.content_mensa.*

class MensaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensa)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val pagerAdapter = MensaPageAdapter(this, supportFragmentManager)

        main_pager.offscreenPageLimit = 2
        main_pager.adapter = pagerAdapter

        mensa_tabs.setupWithViewPager(main_pager)
    }

}
