package app.v.verbundstudium.com.verbundstudiumapp.di

import app.v.verbundstudium.com.verbundstudiumapp.repository.BaseUserRepository
import app.v.verbundstudium.com.verbundstudiumapp.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides @Singleton
    fun provideUserRepository(repo: BaseUserRepository): UserRepository = repo


}