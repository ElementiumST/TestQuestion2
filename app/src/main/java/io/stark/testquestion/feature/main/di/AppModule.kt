package io.stark.testquestion.feature.main.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val applicationContext: Context
) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return applicationContext
    }
}