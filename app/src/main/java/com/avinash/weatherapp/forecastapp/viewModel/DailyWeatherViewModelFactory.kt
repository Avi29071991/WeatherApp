package com.avinash.weatherapp.forecastapp.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.avinash.weatherapp.forecastapp.model.DailyWeatherDataModel

class DailyWeatherViewModelFactory(dailyData: DailyWeatherDataModel) : ViewModelProvider.Factory {

    var dailyDataData: DailyWeatherDataModel?= dailyData

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyDetailViewModel::class.java)) {
            return dailyDataData.let { DailyDetailViewModel(it!!) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}