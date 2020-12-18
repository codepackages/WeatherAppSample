package com.samples.weatherapp.presentation.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.samples.weatherapp.R
import com.samples.weatherapp.presentation.adapter.CustomGridAdapter
import com.scchao.wtrstkpractice.data.model.Weather
import com.scchao.wtrstkpractice.ui.model.CityListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CItyListFragment : Fragment() {
    var mCallback: OnItemClickListener? = null
    var mCitynameInput: EditText? = null
    var mCitiesList: GridView? = null
    fun setOnItemClickListener(callback: OnItemClickListener) {
        this.mCallback = callback
    }

    interface OnItemClickListener {
        fun onItemClick(weather: Weather)
    }

    private val mainViewModel: CityListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        mCitynameInput = root.findViewById(R.id.edittext_cityname)
        mCitiesList = root.findViewById(R.id.list_cities)
        root.findViewById<ImageButton>(R.id.search_button).setOnClickListener {
            val inputText = mCitynameInput?.text.toString() ?: ""
            if (!inputText.isEmpty()) {
                mainViewModel.search(mCitynameInput?.text.toString())
            }
        }
        mainViewModel.preloadData().observe(this, dataObserver)
        mainViewModel.preparedData().observe(this, dataObserver)
        if (!cacheList.isEmpty()) {
            generateList(cacheList)
        } else {
            mainViewModel.preLoadKey()
        }
        return root
    }

    private var cacheList: MutableList<Weather> = mutableListOf()

    private val dataObserver = Observer<MutableList<Weather>> { itData ->
        generateList(itData)
    }

    private fun generateList(list: MutableList<Weather>) {
        context?.let { itContext ->
            cacheList = list
            val gridAdapter = CustomGridAdapter(itContext, list)
            mCitiesList?.adapter = gridAdapter
            mCitiesList?.setOnItemClickListener { parent, view, position, id ->
                val selectData = list.get(position)
                System.out.println("${selectData.location.name} touched")
                mCallback?.onItemClick(selectData)
            }
        }
    }
}