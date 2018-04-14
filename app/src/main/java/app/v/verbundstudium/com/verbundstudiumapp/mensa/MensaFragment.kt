package app.v.verbundstudium.com.verbundstudiumapp.mensa

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.v.verbundstudium.com.verbundstudiumapp.R
import app.v.verbundstudium.com.verbundstudiumapp.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_mensa.*

class MensaFragment : BaseFragment() {

    private val defaultMensaType = MensaType.DORTMUND
    private val dishesGummersbach = mutableListOf<Dish>()
    private val dishesDortmund = mutableListOf<Dish>()
    private var mensaType = defaultMensaType

    init {
        dishesGummersbach.add(Dish("Menü 1", "Frisches Putenschnitzel mit Jägersause und Kartoffelpüree dazu eine Beilage nach Wahl", 4.0, 6.0))
        dishesGummersbach.add(Dish("Menü 2", "Nudeln geschwenk in Champingnonrahm mit frisch geriebenen Grana Padona und einer Beilage nach Wahl", 3.5, 5.5))
        dishesDortmund.add(Dish("Menü 1", "Pizza Hawai", 2.5, 4.0))
        dishesDortmund.add(Dish("Menü 2", "Bunter Salat", 3.5, 5.5))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val typeOrdinal = arguments?.getInt(MENSA_TYPE_KEY, defaultMensaType.ordinal)
                ?: defaultMensaType.ordinal
        mensaType = MensaType.values()[typeOrdinal]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mensa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewManager = LinearLayoutManager(context)
        val viewAdapter = if (mensaType == MensaType.DORTMUND) DishAdapter(dishesDortmund) else DishAdapter(dishesGummersbach)
        mensa_recycler_view.apply {
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
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