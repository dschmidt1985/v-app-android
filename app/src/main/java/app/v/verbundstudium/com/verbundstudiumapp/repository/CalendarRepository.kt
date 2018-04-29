package app.v.verbundstudium.com.verbundstudiumapp.repository

import app.v.verbundstudium.com.verbundstudiumapp.schedule.Schedule
import app.v.verbundstudium.com.verbundstudiumapp.services.CalendarService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

interface CalendarRepository {

    fun loadSchedule(): Observable<Schedule>
}

@Singleton
class BaseCalendarRepository @Inject constructor(private val calendarService: CalendarService) : CalendarRepository {

    override fun loadSchedule(): Observable<Schedule> {
        return calendarService.getSchedule();
    }

}