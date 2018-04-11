package app.v.verbundstudium.com.verbundstudiumapp.interactors

import app.v.verbundstudium.com.verbundstudiumapp.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
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
        return Completable.complete().doOnComplete { userRepo.setUserLoggedIn(true) }.delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
    }

}