package app.v.verbundstudium.com.verbundstudiumapp.interactors

import app.v.verbundstudium.com.verbundstudiumapp.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface LoginInteractor {

    fun doLogin(username: String, password: String): Completable

}

@Singleton
class BaseLoginInteractor @Inject constructor(val userRepo: UserRepository) : LoginInteractor {

    override fun doLogin(username: String, password: String): Completable {
        Timber.v("doLogin with $username ")

        //TODO do login
        return userRepo.loginUser(username, password)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

}