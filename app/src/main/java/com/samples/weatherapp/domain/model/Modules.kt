package com.samples.weatherapp.domain.model

import com.samples.weatherapp.data.repository.WeatherDataRepository
import com.samples.weatherapp.data.setupSharedPreferences
import com.scchao.wtrstkpractice.api.WeatherClient
import com.scchao.wtrstkpractice.api.provideOkHttpClient
import com.scchao.wtrstkpractice.api.provideRestApi
import com.scchao.wtrstkpractice.api.provideRetrofit
import com.scchao.wtrstkpractice.ui.model.DetailsViewModel
import com.scchao.wtrstkpractice.ui.model.CityListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainViewModel = module {
    factory { CityListViewModel(get(), get()) }
}

val detailViewModel = module {
    factory { DetailsViewModel() }
}

val networkModule = module {
    factory { WeatherClient() }
    factory { provideOkHttpClient(get()) }
    factory { provideRestApi(get()) }
    single { provideRetrofit(get()) }
}

val weatherRepoModule = module {
    factory { WeatherDataRepository(get()) }
}

val preferencesModule = module {
    single {
        setupSharedPreferences(
            androidApplication()
        )
    }
}

