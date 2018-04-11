package app.v.verbundstudium.com.verbundstudiumapp.repository

import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    fun isUserLoggedIn(): Boolean
    fun setUserLoggedIn(value: Boolean)
}

@Singleton
class BaseUserRepository @Inject constructor(): UserRepository {

    //TODO use another cache
    companion object {
        private var loggedIn: Boolean = false
    }


    override fun isUserLoggedIn(): Boolean {
        return loggedIn
    }

    override fun setUserLoggedIn(value: Boolean) {
        loggedIn = value
    }
}