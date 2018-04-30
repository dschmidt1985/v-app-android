package app.v.verbundstudium.com.verbundstudiumapp.lessons

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.common.ErrorViewObject
import app.v.verbundstudium.com.verbundstudiumapp.interactors.LessonsInteractor
import io.reactivex.disposables.Disposable
import javax.inject.Inject

sealed class LessonsViewState {
    data class Error(val error: ErrorViewObject) : LessonsViewState()
    class Progress : LessonsViewState()
    data class Success(val lessons: Lessons) : LessonsViewState()
    class Init : LessonsViewState()

}

class LessonsViewModel(private val lessonsInteractor: LessonsInteractor) : ViewModel() {

    var viewState = MutableLiveData<LessonsViewState>()
    var loadDisposable: Disposable? = null

    init {
        viewState.value = LessonsViewState.Init()
    }

    fun loadData() {
        viewState.value = LessonsViewState.Progress()
        loadDisposable = lessonsInteractor.loadLessons().subscribe(
                { viewState.value = LessonsViewState.Success(it) },
                { viewState.value = LessonsViewState.Error(ErrorViewObject("Error during loading schedule", it)) })
    }

    override fun onCleared() {
        loadDisposable?.dispose()
        super.onCleared()
    }
    class LessonsViewModelFactory @Inject constructor(private val lessonsInteractor: LessonsInteractor) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LessonsViewModel::class.java)) {
                return LessonsViewModel(lessonsInteractor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}