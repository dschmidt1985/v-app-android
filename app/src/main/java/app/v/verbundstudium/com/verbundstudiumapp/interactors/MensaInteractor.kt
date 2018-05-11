package app.v.verbundstudium.com.verbundstudiumapp.interactors

import app.v.verbundstudium.com.verbundstudiumapp.mensa.Gericht
import app.v.verbundstudium.com.verbundstudiumapp.mensa.MensaType
import app.v.verbundstudium.com.verbundstudiumapp.repository.MensaRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

interface MensaInteractor {

    fun loadMenu(mensaType: MensaType, dateTime: DateTime): Observable<List<Gericht>>

}

@Singleton
class BaseMensaInteractor @Inject constructor(private val mensaRepo: MensaRepository) : MensaInteractor {
    override fun loadMenu(mensaType: MensaType, dateTime: DateTime): Observable<List<Gericht>> {
        if (dateTime.dayOfWeek == 7) {
            return Observable.just(emptyList())
        } else {
            return mensaRepo.loadDishes(mensaType, dateTime).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

}