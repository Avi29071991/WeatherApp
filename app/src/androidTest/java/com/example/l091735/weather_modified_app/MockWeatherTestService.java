package com.example.l091735.weather_modified_app;

import com.example.l091735.weather_modified_app.model.beans.DailyData;
import com.example.l091735.weather_modified_app.model.beans.DailyMain;
import com.example.l091735.weather_modified_app.model.beans.WeatherBean;
import com.example.l091735.weather_modified_app.model.interfaces.WeatherAPI;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;
import rx.Observable;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by L091735 on 2/11/2016.
 */

public class MockWeatherTestService implements WeatherAPI {


    private final BehaviorDelegate<WeatherAPI> delegate;

    public MockWeatherTestService(BehaviorDelegate<WeatherAPI> delegate) {
        this.delegate = delegate;
    }


    @Override
    public Observable<WeatherBean> fetchCurrentWeather(@Path("api_key") String key, @Path("latitude") double lat, @Path("longitude") double lng) {
        return null;
    }

    @Override
    public Call<WeatherBean> fetchStubbedWeatherBean() throws Exception {

        String response = WebServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), "weather_response_200_OK.json");
        WeatherBean weatherResponse = new Gson().fromJson(response, WeatherBean.class);

        return delegate.returningResponse(weatherResponse).fetchStubbedWeatherBean();
    }
}