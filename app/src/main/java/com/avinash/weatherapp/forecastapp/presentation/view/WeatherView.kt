package com.avinash.weatherapp.forecastapp.presentation.view

import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModel


interface WeatherView : BaseView {


    fun displayNetworkError()

    fun showWeatherData(dataList: List<DailyWeatherViewModel>)

    fun showList(show: Boolean)
}