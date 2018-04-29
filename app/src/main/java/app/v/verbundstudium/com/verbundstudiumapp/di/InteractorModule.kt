package app.v.verbundstudium.com.verbundstudiumapp.di

import app.v.verbundstudium.com.verbundstudiumapp.interactors.BaseLoginInteractor
import app.v.verbundstudium.com.verbundstudiumapp.interactors.BaseMensaInteractor
import app.v.verbundstudium.com.verbundstudiumapp.interactors.LoginInteractor
import app.v.verbundstudium.com.verbundstudiumapp.interactors.MensaInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {
    @Provides
    fun provideLoginInteractor(interactor: BaseLoginInteractor): LoginInteractor = interactor

    @Provides
    fun provideMensaInteractor(interactor: BaseMensaInteractor): MensaInteractor = interactor


}