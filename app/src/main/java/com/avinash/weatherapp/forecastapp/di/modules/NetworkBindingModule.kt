package com.avinash.weatherapp.forecastapp.di.modules

import com.avinash.weatherapp.network.datasource.ApiDataSource
import com.avinash.weatherapp.network.implementation.ApiDataSourceImplementor
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkBindingModule {

    @Binds
    abstract fun bindsApiDataSource(implementor: ApiDataSourceImplementor): ApiDataSource
}