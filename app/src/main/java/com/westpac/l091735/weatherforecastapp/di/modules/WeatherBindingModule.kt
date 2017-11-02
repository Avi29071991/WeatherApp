package com.westpac.l091735.weatherforecastapp.di.modules

import com.westpac.l091735.weatherforecastapp.presentation.activity.WeatherListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeWeatherListActivity(): WeatherListActivity
}