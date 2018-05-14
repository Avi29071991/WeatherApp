package com.avinash.weatherapp.forecastapp.presentation.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.avinash.weatherapp.forecastapp.viewModel.DailyDetailViewModel
import android.arch.lifecycle.Observer
import com.avinash.weatherapp.forecastapp.R
import com.avinash.weatherapp.forecastapp.databinding.ActivityWeatherDetailsBinding
import com.avinash.weatherapp.forecastapp.model.DailyWeatherDataModel
import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModelFactory


class WeatherDetailsActivity : BaseActivity<ActivityWeatherDetailsBinding>() {


    private var weatherDataDetailData: DailyWeatherDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.run {
            initialize()
            setWeatherData()
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_weather_details
    }

    private fun initialize() {
        weatherDataDetailData = getWeatherDailyData()
    }

    private fun setWeatherData() {
        val dataFactory: DailyWeatherViewModelFactory = weatherDataDetailData?.let { DailyWeatherViewModelFactory(it) }!!
        val dailyDetailViewModel: DailyDetailViewModel = ViewModelProviders.of(this, dataFactory).get(DailyDetailViewModel::class.java)
        dailyDetailViewModel.getDailyWeatherData().observe(this, Observer { it ->
            it?.let { it1 ->
                binding.dataDetails = it1
            }
        })
    }

    private fun getWeatherDailyData(): DailyWeatherDataModel? {
        return intent?.extras?.getSerializable(DATA_KEY) as DailyWeatherDataModel?
    }

    companion object {
        val DATA_KEY = "dataKey"
    }
}