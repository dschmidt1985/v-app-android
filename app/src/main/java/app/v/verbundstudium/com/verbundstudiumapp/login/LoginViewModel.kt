package app.v.verbundstudium.com.verbundstudiumapp.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.interactors.LoginInteractor
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel(private val loginInteractor: LoginInteractor): ViewModel() {


    fun doLogin() {
        Timber.v("doLogin")
        loginInteractor.doLogin("username", "password")

    }

    override fun onCleared() {
        Timber.v("onCleared LoginViewModel")
        super.onCleared()
    }

    class LoginViewModelFactory @Inject constructor(private val loginInteractor: LoginInteractor): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(loginInteractor) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}