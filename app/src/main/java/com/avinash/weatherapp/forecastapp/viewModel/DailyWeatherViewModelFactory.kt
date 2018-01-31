package com.avinash.weatherapp.forecastapp.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.avinash.weatherapp.model.Daily

class DailyWeatherViewModelFactory(daily: Daily) : ViewModelProvider.Factory {

    var dailyData:Daily?= daily

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyDetailViewModel::class.java)) {
            return dailyData.let { DailyDetailViewModel(it!!) } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}