package com.avinash.weatherapp.forecastapp.presentation.view

import com.avinash.weatherapp.forecastapp.model.DailyWeatherDataModel


interface WeatherView : BaseView {


    fun displayNetworkError()

    fun showWeatherData(dataList: List<DailyWeatherDataModel>)

    fun showList(show: Boolean)
}