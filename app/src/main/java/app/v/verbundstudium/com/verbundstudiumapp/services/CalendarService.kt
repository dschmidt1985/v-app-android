package app.v.verbundstudium.com.verbundstudiumapp.services

import app.v.verbundstudium.com.verbundstudiumapp.schedule.Schedule
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface CalendarService {

    @Headers("Content-type: application/json")
    @GET("/v1/schedule")
    fun getSchedule():Observable<Schedule>
}