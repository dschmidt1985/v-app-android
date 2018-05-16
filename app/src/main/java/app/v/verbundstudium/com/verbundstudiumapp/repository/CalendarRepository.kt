package app.v.verbundstudium.com.verbundstudiumapp.repository

import app.v.verbundstudium.com.verbundstudiumapp.calendar.Calendar
import app.v.verbundstudium.com.verbundstudiumapp.exams.Exams
import app.v.verbundstudium.com.verbundstudiumapp.lessons.Lessons
import app.v.verbundstudium.com.verbundstudiumapp.services.CalendarService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

interface CalendarRepository {

    fun loadLessons(): Observable<Lessons>
    fun loadExams(): Observable<Exams>
    fun loadCalendarEvents(): Observable<List<Calendar>>
}

//TODO cache events here
@Singleton
class BaseCalendarRepository @Inject constructor(private val calendarService: CalendarService) : CalendarRepository {

    override fun loadLessons(): Observable<Lessons> {
        return calendarService.getLessons()
    }

    override fun loadExams(): Observable<Exams> {
        return calendarService.getExams()
    }

    override fun loadCalendarEvents(): Observable<List<Calendar>> {
        return calendarService.getCalendarEvents()
    }
}