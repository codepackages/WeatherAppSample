package com.scchao.wtrstkpractice.api

import com.samples.weatherapp.BuildConfig.ACCESS_KEY
import com.scchao.wtrstkpractice.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherStackAPI {
    @GET("/current?access_key=${ACCESS_KEY}")
    suspend fun queryCurrentWeather(@Query("query") key: String): Weather
}