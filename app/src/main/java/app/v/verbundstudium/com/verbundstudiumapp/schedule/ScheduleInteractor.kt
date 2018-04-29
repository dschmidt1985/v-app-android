package app.v.verbundstudium.com.verbundstudiumapp.schedule

import app.v.verbundstudium.com.verbundstudiumapp.repository.CalendarRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ScheduleInteractor {
    fun loadSchedule(): Observable<Schedule>
}

class BaseScheduleInteractor @Inject constructor(private val calendarRepository: CalendarRepository): ScheduleInteractor {

    override fun loadSchedule(): Observable<Schedule> {
        return calendarRepository.loadSchedule().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}