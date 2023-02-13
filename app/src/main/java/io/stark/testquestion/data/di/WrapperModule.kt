package io.stark.testquestion.data.di

import dagger.Module
import dagger.Provides
import io.stark.testquestion.data.reposimpl.PersonRepositoryImpl
import io.stark.testquestion.data.wrappers.PersonWrapper
import io.stark.testquestion.data.wrappers.PersonWrapperImpl
import io.stark.testquestion.domain.repos.PersonRepository
import javax.inject.Singleton

@Module
class WrapperModule {
    @Provides
    @Singleton
    fun providePersonWrapper(): PersonWrapper {
        return PersonWrapperImpl()
    }
}