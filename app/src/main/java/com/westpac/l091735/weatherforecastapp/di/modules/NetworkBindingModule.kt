package com.westpac.l091735.weatherforecastapp.di.modules

import com.westpac.l091735.network.datasource.ApiDataSource
import com.westpac.l091735.network.implementation.ApiDataSourceImplementor
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkBindingModule {

    @Binds
    abstract fun bindsApiDataSource(implementor: ApiDataSourceImplementor): ApiDataSource
}