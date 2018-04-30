package app.v.verbundstudium.com.verbundstudiumapp.interactors

import app.v.verbundstudium.com.verbundstudiumapp.calendar.Calendar
import app.v.verbundstudium.com.verbundstudiumapp.repository.CalendarRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface CalendarInteractor {
    fun loadCalendarList(): Observable<List<Calendar>>
}

class BaseCalendarInteractor @Inject constructor(private val calendarRepository: CalendarRepository): CalendarInteractor {


    override fun loadCalendarList(): Observable<List<Calendar>> {
        return calendarRepository.loadCalendarEvents().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}