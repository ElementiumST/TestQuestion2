package io.stark.testquestion.feature.main.di

import dagger.Module
import dagger.Provides
import io.stark.testquestion.domain.di.UseCaseModule
import io.stark.testquestion.domain.usecases.GetPersonListUseCase
import io.stark.testquestion.feature.second.viewmodel.SecondViewModel
import io.stark.testquestion.feature.second.viewmodel.SecondViewModelFactory
import javax.inject.Singleton

@Module(includes = [UseCaseModule::class])
class ViewModelModule {

    @Provides
    @Singleton
    fun provideSecondViewModule(
        getPersonListUseCase: GetPersonListUseCase
    ): SecondViewModelFactory {
        return SecondViewModelFactory(
            getPersonListUseCase
        )
    }
}