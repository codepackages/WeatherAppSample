package com.samples.weatherapp.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.samples.weatherapp.R
import com.scchao.wtrstkpractice.data.model.Weather

class

CustomCardView @JvmOverloads constructor(
    context: Context,
    val data: Weather,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        initView(context)
    }

    private var mCityname: TextView? = null
    private var mTemperature: TextView? = null
    private var mDescription: TextView? = null
    private var mImage: ImageView? = null

    private fun initView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_card_layout, this)
        mCityname = view.findViewById(R.id.city_name)
        mCityname?.text = data.location.name
        mTemperature = view.findViewById(R.id.temperature)
        mTemperature?.text = "${data.current.temperature}\u2103"
        mDescription = view.findViewById(R.id.description)
        val descrips = data.current.weather_descriptions
        if (descrips.size > 0) {
            mDescription?.text = descrips.get(0)
        }
        mImage = view.findViewById(R.id.status_image)
        val images = data.current.weather_icons
        if (images.size > 0) {
            Glide.with(context).load(images.get(0)).into(mImage)
        }

    }

}
