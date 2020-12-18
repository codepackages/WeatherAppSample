package com.samples.weatherapp.presentation.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.samples.weatherapp.presentation.view.CustomCardView
import com.scchao.wtrstkpractice.data.model.Weather

class CustomGridAdapter @JvmOverloads constructor(
    var context: Context,
    var weatherList: List<Weather>
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var weatherItem = weatherList[position]
        return CustomCardView(context, weatherItem)
    }

    override fun getItem(position: Int): Any {
        return weatherList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return weatherList.size
    }
}