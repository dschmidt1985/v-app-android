package app.v.verbundstudium.com.verbundstudiumapp.exams

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.common.ErrorViewObject
import app.v.verbundstudium.com.verbundstudiumapp.interactors.ExamsInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

sealed class ExamsViewState {
    data class Error(val error: ErrorViewObject) : ExamsViewState()
    class Progress : ExamsViewState()
    data class Success(val exams: Exams) : ExamsViewState()
    class Init : ExamsViewState()

}

class ExamsViewModel(private val examsInteractor: ExamsInteractor) : ViewModel() {

    var viewState = MutableLiveData<ExamsViewState>()
    var loadDisposable: Disposable? = null

    init {
        viewState.value = ExamsViewState.Init()
    }

    fun loadData() {
        viewState.value = ExamsViewState.Progress()
        loadDisposable = examsInteractor.loadExams().subscribe(
                { viewState.value = ExamsViewState.Success(it) },
                { viewState.value = ExamsViewState.Error(ErrorViewObject("Error during loading schedule", it)) })
    }

    override fun onCleared() {
        loadDisposable?.dispose()
        super.onCleared()
    }

    class ExamsViewModelFactory @Inject constructor(private val examsInteractor: ExamsInteractor) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExamsViewModel::class.java)) {
                return ExamsViewModel(examsInteractor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}