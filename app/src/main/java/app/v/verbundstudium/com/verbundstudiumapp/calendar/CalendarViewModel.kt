package app.v.verbundstudium.com.verbundstudiumapp.calendar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.common.ErrorViewObject
import app.v.verbundstudium.com.verbundstudiumapp.interactors.CalendarInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

sealed class CalendarViewState {
    data class Error(val error: ErrorViewObject) : CalendarViewState()
    class Progress : CalendarViewState()
    data class Success(val calendars: List<Calendar>) : CalendarViewState()
    class Init : CalendarViewState()

}

class CalendarViewModel(private val calendarInteractor: CalendarInteractor) : ViewModel() {

    var viewState = MutableLiveData<CalendarViewState>()
    var loadDisposable: Disposable? = null

    init {
        viewState.value = CalendarViewState.Init()
    }

    fun loadData() {
        viewState.value = CalendarViewState.Progress()
        loadDisposable = calendarInteractor.loadCalendarList().subscribe(
                { viewState.value = CalendarViewState.Success(it) },
                { viewState.value = CalendarViewState.Error(ErrorViewObject("Error during loading schedule", it)) })
    }

    override fun onCleared() {
        loadDisposable?.dispose()
        super.onCleared()
    }

    class CalendarViewModelFactory @Inject constructor(private val calendarInteractor: CalendarInteractor) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CalendarViewModel::class.java)) {
                return CalendarViewModel(calendarInteractor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}