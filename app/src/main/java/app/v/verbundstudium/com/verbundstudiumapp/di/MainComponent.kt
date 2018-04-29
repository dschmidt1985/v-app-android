package app.v.verbundstudium.com.verbundstudiumapp.di

import app.v.verbundstudium.com.verbundstudiumapp.MainActivity
import app.v.verbundstudium.com.verbundstudiumapp.login.LoginActivity
import app.v.verbundstudium.com.verbundstudiumapp.mensa.MensaFragment
import app.v.verbundstudium.com.verbundstudiumapp.schedule.ScheduleActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InteractorModule::class, RepositoryModule::class, ServiceModule::class])
interface MainComponent {

    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: ScheduleActivity)
    fun inject(fragment: MensaFragment)

}