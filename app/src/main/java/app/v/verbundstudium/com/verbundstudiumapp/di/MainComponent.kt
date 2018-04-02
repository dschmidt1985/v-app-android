package app.v.verbundstudium.com.verbundstudiumapp.di

import app.v.verbundstudium.com.verbundstudiumapp.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(InteractorModule::class)])
interface MainComponent {

    fun inject(activity: LoginActivity)

}