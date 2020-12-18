package com.samples.weatherapp.presentation.home.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.samples.weatherapp.R
import com.samples.weatherapp.presentation.adapter.CustomArrayAdapter
import com.scchao.wtrstkpractice.data.model.Weather
import com.scchao.wtrstkpractice.ui.model.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailsFragment: Fragment() {
    var mCityname: TextView? = null
    var mIcon: ImageView? = null
    var mTemperature: TextView? = null
    var mWeekList: ListView? = null
    var mDaysOfWeek = Array<String>(6){""}

    private val detailViewModel: DetailsViewModel by viewModel()

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        mContext = (AppCompatActivity) context;
    }

    private var weatherData: Weather? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inputStr = arguments?.getString("detail_prop")
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        mCityname = root.findViewById(R.id.title_cityname)
        mIcon = root.findViewById(R.id.icon_image)
        mTemperature = root.findViewById(R.id.title_temperature)
        mWeekList = root.findViewById(R.id.listView)
        generateDays()
        detailViewModel.liveLocation().observe(this, locationObserver)
        detailViewModel.liveImgUrl().observe(this, iconObserver)
        detailViewModel.liveTempText().observe(this, temperObserver)
        weatherData?.let {
            detailViewModel.setWeatherData(it)
        } ?: run {
            detailViewModel.setWeatherData(arguments?.getSerializable("weather_detail") as Weather?)
        }
        return root
    }

    fun generateDays(): Unit {
        var sCalendar = Calendar.getInstance()
        for (i in 0..5) {
            sCalendar.add(Calendar.DATE, 1)
            mDaysOfWeek[i] = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        }
    }

    private val locationObserver = Observer<String> { str ->
        mCityname?.text = str
    }


    private val temperObserver = Observer<String> { str ->
        mTemperature?.text = str
        val myListAdapter = activity?.let { CustomArrayAdapter(it, mDaysOfWeek,
            str) }
        mWeekList!!.adapter = myListAdapter
    }


    private val iconObserver = Observer<String> { str ->
        str?.let {
            if (it.length > 0) {
                mIcon?.let { view ->
                    Glide.with(context).load(it).into(view)
                }
            }
        }
    }


}