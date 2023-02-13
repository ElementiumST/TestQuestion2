package io.stark.testquestion.domain.di

import dagger.Module
import dagger.Provides
import io.stark.testquestion.data.di.RepositoryModule
import io.stark.testquestion.domain.repos.PersonRepository
import io.stark.testquestion.domain.usecases.GetPersonListUseCase
import io.stark.testquestion.domain.usecases.GetPersonListUseCaseImpl
import javax.inject.Singleton

@Module(includes = [ RepositoryModule::class ])
class UseCaseModule {
    @Provides
    @Singleton
    fun providePersonUseCase(
        personRepository: PersonRepository
    ): GetPersonListUseCase {
        return GetPersonListUseCaseImpl(personRepository)
    }
}