package app.v.verbundstudium.com.verbundstudiumapp.repository

import app.v.verbundstudium.com.verbundstudiumapp.services.AccessHandler
import app.v.verbundstudium.com.verbundstudiumapp.services.UserService
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    fun isUserLoggedIn(): Boolean

    fun loginUser(username: String, password: String): Completable
}

@Singleton
class BaseUserRepository @Inject constructor(private val accessHandler: AccessHandler,
                                             private val userService: UserService) : UserRepository {

    override fun isUserLoggedIn(): Boolean {
        return accessHandler.isLoggedIn()
    }

    override fun loginUser(username: String, password: String): Completable {
        return userService.loginUser(UserService.LoginBody(username, password)).doOnNext { it -> accessHandler.setToken(it) }.ignoreElements()
    }
}