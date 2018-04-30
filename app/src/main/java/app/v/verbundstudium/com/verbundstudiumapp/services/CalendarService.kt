package app.v.verbundstudium.com.verbundstudiumapp.services

import app.v.verbundstudium.com.verbundstudiumapp.calendar.Calendar
import app.v.verbundstudium.com.verbundstudiumapp.exams.Exams
import app.v.verbundstudium.com.verbundstudiumapp.lessons.Lessons
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface CalendarService {

    @Headers("Content-type: application/json")
    @GET("/v1/lessons")
    fun getLessons():Observable<Lessons>

    @Headers("Content-type: application/json")
    @GET("/v1/exams")
    fun getExams():Observable<Exams>

    @Headers("Content-type: application/json")
    @GET("/v1/calendar")
    fun getCalendarEvents():Observable<List<Calendar>>


}