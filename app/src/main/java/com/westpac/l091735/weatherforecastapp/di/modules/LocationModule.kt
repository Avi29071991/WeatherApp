package com.westpac.l091735.weatherforecastapp.di.modules

import android.app.Application
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.westpac.l091735.weatherforecastapp.utils.Codes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {


    @Provides
    @Singleton
    internal fun providesLocationRequestInstance(): LocationRequest {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        locationRequest.interval = Codes.LOCATION_INTERVAL_TIME
        locationRequest.fastestInterval = Codes.LOCATION_FASTEST_INTERVAL_TIME

        return locationRequest
    }

    @Provides
    @Singleton
    internal fun providesGoogleApiClientBuilderInstance(application: Application): GoogleApiClient.Builder {
        return GoogleApiClient.Builder(application).addApi(LocationServices.API)
    }
}