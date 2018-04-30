package app.v.verbundstudium.com.verbundstudiumapp.di

import app.v.verbundstudium.com.verbundstudiumapp.interactors.*
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideLoginInteractor(interactor: BaseLoginInteractor): LoginInteractor = interactor

    @Provides
    fun provideMensaInteractor(interactor: BaseMensaInteractor): MensaInteractor = interactor

    @Provides
    fun provideLessonsInteractor(interactor: BaseLessonsInteractor): LessonsInteractor = interactor

    @Provides
    fun provideExamsInteractor(interactor: BaseExamsInteractor): ExamsInteractor = interactor

    @Provides
    fun provideCalendarInteractor(interactor: BaseCalendarInteractor): CalendarInteractor = interactor


}