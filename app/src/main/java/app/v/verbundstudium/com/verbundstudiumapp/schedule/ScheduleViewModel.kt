package app.v.verbundstudium.com.verbundstudiumapp.schedule

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.common.ErrorViewObject
import io.reactivex.disposables.Disposable
import javax.inject.Inject

sealed class ScheduleViewState {
    data class Error(val error: ErrorViewObject) : ScheduleViewState()
    class Progress : ScheduleViewState()
    data class Success(val schedule: Schedule) : ScheduleViewState()
    class Init : ScheduleViewState()

}

class ScheduleViewModel(private val scheduleInteractor: ScheduleInteractor) : ViewModel() {

    var viewState = MutableLiveData<ScheduleViewState>()
    var loadDisposable: Disposable? = null

    init {
        viewState.value = ScheduleViewState.Init()
    }

    fun loadData() {
        viewState.value = ScheduleViewState.Progress()
        loadDisposable = scheduleInteractor.loadSchedule().subscribe(
                { viewState.value = ScheduleViewState.Success(it) },
                { viewState.value = ScheduleViewState.Error(ErrorViewObject("Error during loading schedule", it)) })
    }

    override fun onCleared() {
        loadDisposable?.dispose()
        super.onCleared()
    }
    class ScheduleViewModelFactory @Inject constructor(private val scheduleInteractor: ScheduleInteractor) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
                return ScheduleViewModel(scheduleInteractor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}