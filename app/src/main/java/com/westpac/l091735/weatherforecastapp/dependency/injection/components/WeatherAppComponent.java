package com.westpac.l091735.weatherforecastapp.dependency.injection.components;

import com.westpac.l091735.weatherforecastapp.dependency.injection.modules.AndroidModule;
import com.westpac.l091735.weatherforecastapp.dependency.injection.modules.LocationModule;
import com.westpac.l091735.weatherforecastapp.dependency.injection.modules.NetworkingModules;
import com.westpac.l091735.weatherforecastapp.presenter.WeatherImpl;
import com.westpac.l091735.weatherforecastapp.view.activity.WeatherMainActivity;
import com.westpac.l091735.weatherforecastapp.view.adapter.DailyWeatherAdapter;

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
