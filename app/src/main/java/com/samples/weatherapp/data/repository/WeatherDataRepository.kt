package com.samples.weatherapp.data.repository

import com.scchao.wtrstkpractice.api.WeatherStackAPI

class WeatherDataRepository(private val weatherAPI: WeatherStackAPI) {
    suspend fun queryWeather(key: String) = weatherAPI.queryCurrentWeather(key)
}