package com.example.l091735.weather_modified_app.dagggerImpPackage;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by L091735 on 28/10/2016.
 */

@Module
public class LocationModule {

    Application application;

    public LocationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public LocationRequest getLocationRequestInstance() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(3000);
        locationRequest.setFastestInterval(2000);

        return locationRequest;
    }

    @Provides
    @Singleton
    public GoogleApiClient.Builder getGoogleApiClientBuilderInstance() {

        return new GoogleApiClient.Builder(application).addApi(LocationServices.API);
    }
}
