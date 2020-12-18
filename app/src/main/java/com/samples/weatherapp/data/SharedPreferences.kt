package com.samples.weatherapp.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCE_KEY = "com.scchao.wtrdtk_preferences"

fun setupSharedPreferences(app: Application): SharedPreferences = app.getSharedPreferences(
    PREFERENCE_KEY, Context.MODE_PRIVATE
)