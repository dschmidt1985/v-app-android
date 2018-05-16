package app.v.verbundstudium.com.verbundstudiumapp.services

import app.v.verbundstudium.com.verbundstudiumapp.mensa.Gericht
import app.v.verbundstudium.com.verbundstudiumapp.mensa.MensaType
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MensaService {

    @Headers("Content-type: application/json")
    @GET("/v1/mensa/{city}/{day}")
    fun getMenu(@Path("city") city: MensaType, @Path("day") date: Long):Observable<List<Gericht>>
}