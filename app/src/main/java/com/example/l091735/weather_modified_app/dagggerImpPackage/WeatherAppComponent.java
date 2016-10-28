package com.example.l091735.weather_modified_app.dagggerImpPackage;

import com.example.l091735.weather_modified_app.presenter.WeatherImpl;
import com.example.l091735.weather_modified_app.view.activity.WeatherMainActivity;
import com.example.l091735.weather_modified_app.view.adapter.DailyWeatherAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by L091735 on 28/10/2016.
 */

@Singleton
@Component(modules = {AndroidModule.class, NetworkingModules.class, LocationModule.class})
public interface WeatherAppComponent {

    void injectWeatherActivity(WeatherMainActivity weatherMainActivity);

    void injectWeatherListAdapter(DailyWeatherAdapter dailyWeatherAdapter);

    void injectPojo(WeatherImpl weatherImp);
}
