package com.westpac.l091735.weatherforecastapp.application;

import android.app.Application;

import com.westpac.l091735.weatherforecastapp.dependency.injection.components.DaggerWeatherAppComponent;
import com.westpac.l091735.weatherforecastapp.dependency.injection.components.WeatherAppComponent;
import com.westpac.l091735.weatherforecastapp.dependency.injection.modules.AndroidModule;
import com.westpac.l091735.weatherforecastapp.dependency.injection.modules.LocationModule;
import com.westpac.l091735.weatherforecastapp.dependency.injection.modules.NetworkingModules;
import com.westpac.l091735.weatherforecastapp.utils.Codes;


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
