package io.stark.testquestion.data.di

import dagger.Module
import dagger.Provides
import io.stark.testquestion.data.datasource.NetworkApi
import io.stark.testquestion.data.reposimpl.PersonRepositoryImpl
import io.stark.testquestion.data.wrappers.PersonWrapper
import io.stark.testquestion.domain.repos.PersonRepository
import javax.inject.Singleton

@Module(includes = [WrapperModule::class])
class RepositoryModule {
    @Singleton
    @Provides
    fun providePersonRepository(
        networkApi: NetworkApi,
        personWrapper: PersonWrapper
    ): PersonRepository {
        return PersonRepositoryImpl(networkApi, personWrapper)
    }
}