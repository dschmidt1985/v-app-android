package app.v.verbundstudium.com.verbundstudiumapp.interactors

import app.v.verbundstudium.com.verbundstudiumapp.lessons.Lessons
import app.v.verbundstudium.com.verbundstudiumapp.repository.CalendarRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface LessonsInteractor {
    fun loadLessons(): Observable<Lessons>
}

class BaseLessonsInteractor @Inject constructor(private val calendarRepository: CalendarRepository): LessonsInteractor {

    override fun loadLessons(): Observable<Lessons> {
        return calendarRepository.loadLessons().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}