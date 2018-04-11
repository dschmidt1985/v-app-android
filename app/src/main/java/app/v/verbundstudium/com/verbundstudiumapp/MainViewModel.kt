package app.v.verbundstudium.com.verbundstudiumapp

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import app.v.verbundstudium.com.verbundstudiumapp.repository.UserRepository
import javax.inject.Inject

class MainViewModel(private val userRepo: UserRepository): ViewModel() {

    fun isUserLoggedIn(): Boolean = userRepo.isUserLoggedIn()

    class MainViewModelFactory @Inject constructor(private val userRepo: UserRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(userRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}