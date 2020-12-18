package com.samples.weatherapp.presentation.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class CustomArrayAdapter(private val context: Activity, private val day: Array<String>, private val temperature: String)
    : ArrayAdapter<String>(context, com.samples.weatherapp.R.layout.custom_list_layout, day) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(com.samples.weatherapp.R.layout.custom_list_layout, null, true)

        val titleText = rowView.findViewById(com.samples.weatherapp.R.id.day_of_week) as TextView
        val subtitleText = rowView.findViewById(com.samples.weatherapp.R.id.day_temperature) as TextView

        titleText.text = day[position]
        subtitleText.text = temperature

        return rowView
    }
}