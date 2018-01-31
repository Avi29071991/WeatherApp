package com.avinash.weatherapp.forecastapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


@Module
abstract class AndroidBindingModule {

    @Binds
    abstract fun bindsContext(app: Application): Context
}