package app.v.verbundstudium.com.verbundstudiumapp.repository

import app.v.verbundstudium.com.verbundstudiumapp.mensa.Gericht
import app.v.verbundstudium.com.verbundstudiumapp.mensa.MensaType
import app.v.verbundstudium.com.verbundstudiumapp.services.MensaService
import io.reactivex.Observable
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

interface MensaRepository {

    fun loadMenu(mensaType: MensaType, dateTime: DateTime): Observable<List<Gericht>>
}

@Singleton
class BaseMensaRepository @Inject constructor(private val mensaService: MensaService) : MensaRepository {

    override fun loadMenu(mensaType: MensaType, dateTime: DateTime): Observable<List<Gericht>> {
        return mensaService.getMenu(mensaType, dateTime.millis)
    }

}