package app.v.verbundstudium.com.verbundstudiumapp.interactors

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface LoginInteractor {

    fun doLogin(username: String, password: String)

}

@Singleton
class BaseLoginInteractor @Inject constructor(): LoginInteractor {
    override fun doLogin(username: String, password: String) {
        Timber.v("doLogin with $username and $password")
    }

}