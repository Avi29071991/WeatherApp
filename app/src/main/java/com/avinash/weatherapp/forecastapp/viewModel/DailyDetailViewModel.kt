package com.avinash.weatherapp.forecastapp.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.avinash.weatherapp.forecastapp.model.DailyWeatherDataModel

class DailyDetailViewModel(dailyDetailData: DailyWeatherDataModel) : ViewModel() {

    private var dailyWeatherData = MutableLiveData<DailyWeatherDataModel>()

    init {
        dailyWeatherData.value = dailyDetailData
    }

    fun getDailyWeatherData(): LiveData<DailyWeatherDataModel> {
        return dailyWeatherData
    }
}