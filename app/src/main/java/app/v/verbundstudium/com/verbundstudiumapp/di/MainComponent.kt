package app.v.verbundstudium.com.verbundstudiumapp.di

import app.v.verbundstudium.com.verbundstudiumapp.MainActivity
import app.v.verbundstudium.com.verbundstudiumapp.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InteractorModule::class, RepositoryModule::class])
interface MainComponent {

    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)

}