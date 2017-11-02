package com.westpac.l091735.weatherforecastapp.di

import android.app.Application
import com.westpac.l091735.weatherforecastapp.application.WeatherApplication
import com.westpac.l091735.weatherforecastapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidBindingModule::class, AndroidModule::class, NetworkingModule::class, LocationModule::class, NetworkBindingModule::class, WeatherBindingModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: WeatherApplication)
}