package app.v.verbundstudium.com.verbundstudiumapp.repository

import app.v.verbundstudium.com.verbundstudiumapp.mensa.Dish
import app.v.verbundstudium.com.verbundstudiumapp.mensa.MensaType
import app.v.verbundstudium.com.verbundstudiumapp.services.MensaService
import io.reactivex.Observable
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

interface MensaRepository {

    fun loadDishes(mensaType: MensaType, dateTime: DateTime): Observable<List<Dish>>
}

@Singleton
class BaseMensaRepository @Inject constructor(private val mensaService: MensaService) : MensaRepository {

    override fun loadDishes(mensaType: MensaType, dateTime: DateTime): Observable<List<Dish>> {
        return mensaService.getDishes(mensaType, dateTime.millis)
    }

}