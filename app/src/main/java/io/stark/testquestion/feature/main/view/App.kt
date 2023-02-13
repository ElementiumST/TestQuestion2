package io.stark.testquestion.feature.main.view

import android.app.Application
import io.stark.testquestion.R
import io.stark.testquestion.feature.main.di.AppComponent
import io.stark.testquestion.feature.main.di.AppModule
import io.stark.testquestion.feature.main.di.DaggerAppComponent
import io.stark.testquestion.feature.main.di.NetworkModule

class App: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(getString(R.string.server_url)))
            .build()
    }
}