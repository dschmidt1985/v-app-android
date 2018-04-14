package app.v.verbundstudium.com.verbundstudiumapp.mensa

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import app.v.verbundstudium.com.verbundstudiumapp.R
import java.util.*

class MensaPageAdapter(private val context: Context, private val fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments = ArrayList<Fragment>()

    private val pageTitles = arrayOf(
            context.getString(R.string.tab_mensa_dortmund),
            context.getString(R.string.tab_mensa_gummersbach))


    init {
        pageTitles.indices.mapTo(fragments) { MensaPagerFragmentFactory.create(it) }
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return pageTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pageTitles[position]
    }


    private object MensaPagerFragmentFactory {
        internal fun create(position: Int): Fragment {
            when (position) {
                0 -> return MensaFragment.newInstance(MensaType.DORTMUND)
                1 -> return MensaFragment.newInstance(MensaType.GUMMERSBACH)
                else -> throw IllegalArgumentException("No Fragment for Position")
            }
        }
    }

}