package app.v.verbundstudium.com.verbundstudiumapp.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.common.ErrorViewObject
import app.v.verbundstudium.com.verbundstudiumapp.interactors.LoginInteractor
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

sealed class LoginViewState {
    data class Error(val error: ErrorViewObject) : LoginViewState()
    class Progress : LoginViewState()
    class Success : LoginViewState()
    class Init : LoginViewState()
}

class LoginViewModel(private val loginInteractor: LoginInteractor) : ViewModel() {


    private var loginViewState: MutableLiveData<LoginViewState>? = null
    var loginDisposable: Disposable? = null

    fun getlLoginViewState(): MutableLiveData<LoginViewState> {
        if (loginViewState == null) {
            loginViewState = MutableLiveData()
            loginViewState!!.value = LoginViewState.Init()
        }
        return loginViewState!!
    }

    fun doLogin() {
        getlLoginViewState().value = LoginViewState.Progress()
        Timber.v("doLogin")
        //TODO use input fields as value
        loginDisposable = loginInteractor.doLogin("root", "ilias.test").subscribe(
                { getlLoginViewState().value = LoginViewState.Success() },
                { t -> getlLoginViewState().value = LoginViewState.Error(ErrorViewObject("Login Error... ${t.message}", t)) })

    }

    override fun onCleared() {
        Timber.v("onCleared LoginViewModel")
        loginDisposable?.dispose()
        super.onCleared()
    }

    class LoginViewModelFactory @Inject constructor(private val loginInteractor: LoginInteractor) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(loginInteractor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}