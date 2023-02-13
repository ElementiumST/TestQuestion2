package io.stark.testquestion.feature.main.di

import dagger.Component
import io.stark.testquestion.feature.second.view.SecondFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(secondFragment: SecondFragment)


}