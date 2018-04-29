package app.v.verbundstudium.com.verbundstudiumapp.mensa

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.common.ErrorViewObject
import app.v.verbundstudium.com.verbundstudiumapp.interactors.MensaInteractor
import io.reactivex.disposables.Disposable
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject

sealed class MensaViewState {
    data class Error(val error: ErrorViewObject) : MensaViewState()
    class Progress : MensaViewState()
    data class Success(val dishes: List<Dish>) : MensaViewState()
    class Init : MensaViewState()
}

class MensaViewModel(private val mensaInteractor: MensaInteractor) : ViewModel() {

    private var mensaType = MensaType.DORTMUND

    var dateLiveData = MutableLiveData<DateTime>()
    var viewState =  MutableLiveData<MensaViewState>()
    var loadDisposable: Disposable? = null

    init {
        dateLiveData.value = DateTime()
        viewState.value = MensaViewState.Init()
    }

    fun setMensaType(type: MensaType) {
        mensaType = type
    }

    fun loadData() {
        viewState.value = MensaViewState.Progress()
        loadDisposable = mensaInteractor.loadMenu(mensaType, dateLiveData.value!!).subscribe(
                {viewState.value = MensaViewState.Success(it)},
                {viewState.value = MensaViewState.Error(ErrorViewObject("Fehler beim laden des Mensa Speiseplans!", it))}
        )
    }

    override fun onCleared() {
        Timber.v("onCleared MensaViewModel")
        loadDisposable?.dispose()
        super.onCleared()
    }

    class MensaViewModelFactory @Inject constructor(private val mensaInteractor: MensaInteractor) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MensaViewModel::class.java)) {
                return MensaViewModel(mensaInteractor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}