package com.example.l091735.weather_modified_app.application;

import android.app.Application;

import com.example.l091735.weather_modified_app.R;
import com.example.l091735.weather_modified_app.dagggerImpPackage.AndroidModule;
import com.example.l091735.weather_modified_app.dagggerImpPackage.DaggerWeatherAppComponent;
import com.example.l091735.weather_modified_app.dagggerImpPackage.LocationModule;
import com.example.l091735.weather_modified_app.dagggerImpPackage.NetworkingModules;
import com.example.l091735.weather_modified_app.dagggerImpPackage.WeatherAppComponent;
import com.example.l091735.weather_modified_app.utils.Codes;


/**
 * Created by L091735 on 28/10/2016.
 */

public class MyWeatherApplication extends Application {

    WeatherAppComponent appComponent;

    public WeatherAppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerWeatherAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .networkingModules(new NetworkingModules(Codes.BASE_WEATHER_URL))
                .locationModule(new LocationModule(this))
                .build();
    }
}
