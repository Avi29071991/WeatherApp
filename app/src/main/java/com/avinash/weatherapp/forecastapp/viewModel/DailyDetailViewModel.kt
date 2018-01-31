package com.avinash.weatherapp.forecastapp.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.avinash.weatherapp.model.Daily

class DailyDetailViewModel(dailyDetail: Daily) : ViewModel() {

    private var dailyWeatherData = MutableLiveData<Daily>()

    init {
        dailyWeatherData.value = dailyDetail
    }

    fun getDailyWeatherData(): LiveData<Daily> {
        return dailyWeatherData
    }
}