package com.example.l091735.weather_modified_app.application;

import android.app.Application;

import com.example.l091735.weather_modified_app.dependency.injection.components.DaggerWeatherAppComponent;
import com.example.l091735.weather_modified_app.dependency.injection.components.WeatherAppComponent;
import com.example.l091735.weather_modified_app.dependency.injection.modules.AndroidModule;
import com.example.l091735.weather_modified_app.dependency.injection.modules.LocationModule;
import com.example.l091735.weather_modified_app.dependency.injection.modules.NetworkingModules;
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
