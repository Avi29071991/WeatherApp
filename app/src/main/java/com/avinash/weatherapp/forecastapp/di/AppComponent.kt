package com.avinash.weatherapp.forecastapp.di

import android.app.Application
import com.avinash.weatherapp.forecastapp.application.WeatherApplication
import com.avinash.weatherapp.forecastapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidBindingModule::class, AndroidModule::class, NetworkingModule::class, LocationModule::class, NetworkBindingModule::class, WeatherBindingModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }

    fun inject(app: WeatherApplication)
}