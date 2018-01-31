package com.avinash.weatherapp.forecastapp.presentation.activity

import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.avinash.weatherapp.forecastapp.viewModel.DailyDetailViewModel
import com.avinash.weatherapp.model.Daily
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import com.avinash.weatherapp.forecastapp.R
import com.avinash.weatherapp.forecastapp.databinding.ActivityWeatherDetailsBinding
import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModel
import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModelFactory


class WeatherDetailsActivity : BaseActivity<ActivityWeatherDetailsBinding>(), LifecycleRegistryOwner {


    private var weatherDetailData: Daily? = null

    private lateinit var registry: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        setWeatherData()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_weather_details
    }

    private fun initialize() {
        registry = LifecycleRegistry(this)
        weatherDetailData = getWeatherDailyData()
    }

    private fun setWeatherData() {
        val dataFactory: DailyWeatherViewModelFactory = weatherDetailData?.let { DailyWeatherViewModelFactory(it) }!!
        val dailyDetailViewModel: DailyDetailViewModel = ViewModelProviders.of(this, dataFactory).get(DailyDetailViewModel::class.java)
        dailyDetailViewModel.getDailyWeatherData().observe(this, Observer { it ->
            it?.let { it1 ->
                binding.dataDetails = DailyWeatherViewModel(it1)
            }
        })
    }

    private fun getWeatherDailyData(): Daily? {
        return intent?.extras?.getSerializable(DATA_KEY) as Daily?
    }

    override fun getLifecycle(): LifecycleRegistry {
        return registry
    }

    companion object {
        val DATA_KEY = "dataKey"
    }
}