package com.scchao.wtrstkpractice.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun baseUrl(): String {
    return "http://api.weatherstack.com/"
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl()).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: WeatherClient): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideRestApi(retrofit: Retrofit): WeatherStackAPI = retrofit.create(WeatherStackAPI::class.java)

class WeatherClient : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .build()
        return chain.proceed(newRequest)
    }
}
