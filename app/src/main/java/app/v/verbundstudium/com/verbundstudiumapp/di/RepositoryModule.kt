package app.v.verbundstudium.com.verbundstudiumapp.di

import app.v.verbundstudium.com.verbundstudiumapp.repository.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides @Singleton
    fun provideUserRepository(repo: BaseUserRepository): UserRepository = repo

    @Provides @Singleton
    fun provideMensaRepository(repo: BaseMensaRepository): MensaRepository = repo

    @Provides @Singleton
    fun provideCalendarRepository(repo: BaseCalendarRepository): CalendarRepository = repo


}