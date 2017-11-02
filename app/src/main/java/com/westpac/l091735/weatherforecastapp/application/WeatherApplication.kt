package com.westpac.l091735.weatherforecastapp.application

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import com.westpac.l091735.weatherforecastapp.di.DaggerAppComponent
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
    fun setDispatchingAndroidInjector(dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>){
        this.dispatchingAndroidInjector = dispatchingAndroidInjector
    }
}