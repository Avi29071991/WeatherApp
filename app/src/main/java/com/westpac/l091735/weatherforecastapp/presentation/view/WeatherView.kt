package com.westpac.l091735.weatherforecastapp.presentation.view

import com.westpac.l091735.weatherforecastapp.viewModel.DailyWeatherViewModel


interface WeatherView : BaseView {


    fun displayNetworkError()

    fun showWeatherData(dataList: List<DailyWeatherViewModel>)

    fun showList(show: Boolean)
}