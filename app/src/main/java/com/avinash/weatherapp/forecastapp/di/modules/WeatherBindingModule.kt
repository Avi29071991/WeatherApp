package com.avinash.weatherapp.forecastapp.di.modules

import com.avinash.weatherapp.forecastapp.presentation.activity.WeatherListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeWeatherListActivity(): WeatherListActivity
}