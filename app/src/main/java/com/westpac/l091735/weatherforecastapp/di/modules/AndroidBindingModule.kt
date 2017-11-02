package com.westpac.l091735.weatherforecastapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


@Module
abstract class AndroidBindingModule {

    @Binds
    abstract fun bindsContext(app: Application): Context
}