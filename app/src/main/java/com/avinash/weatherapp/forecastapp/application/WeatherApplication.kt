package com.avinash.weatherapp.forecastapp.application

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import com.avinash.weatherapp.forecastapp.di.DaggerAppComponent
import javax.inject.Inject

class WeatherApplication : Application(), HasActivityInjector {

    private lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build().inject(this)

    }

    @Inject
    fun setDispatchingAndroidInjector(dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>) {
        this.dispatchingAndroidInjector = dispatchingAndroidInjector
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}