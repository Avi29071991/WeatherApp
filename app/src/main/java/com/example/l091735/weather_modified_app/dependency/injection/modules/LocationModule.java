package com.example.l091735.weather_modified_app.dependency.injection.modules;

import android.app.Application;

import com.example.l091735.weather_modified_app.utils.Codes;
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

    private Application application;

    public LocationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    LocationRequest getLocationRequestInstance() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(Codes.LOCATION_INTERVAL_TIME);
        locationRequest.setFastestInterval(Codes.LOCATION_FASTEST_INTERVAL_TIME);

        return locationRequest;
    }

    @Provides
    @Singleton
    GoogleApiClient.Builder getGoogleApiClientBuilderInstance() {

        return new GoogleApiClient.Builder(application).addApi(LocationServices.API);
    }
}
