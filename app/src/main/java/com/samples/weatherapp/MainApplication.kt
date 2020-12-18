package com.samples.weatherapp

import android.app.Application
import com.samples.weatherapp.domain.model.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    mainViewModel,
                    detailViewModel,
                    networkModule,
                    weatherRepoModule,
                    preferencesModule
                )
            )
        }
    }
}