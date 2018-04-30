package app.v.verbundstudium.com.verbundstudiumapp.interactors

import app.v.verbundstudium.com.verbundstudiumapp.exams.Exams
import app.v.verbundstudium.com.verbundstudiumapp.repository.CalendarRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ExamsInteractor {
    fun loadExams(): Observable<Exams>
}

class BaseExamsInteractor @Inject constructor(private val calendarRepository: CalendarRepository): ExamsInteractor {

    override fun loadExams(): Observable<Exams> {
        return calendarRepository.loadExams().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

}