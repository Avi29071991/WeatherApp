package com.example.l091735.weather_modified_app.model.interfaces;

import com.example.l091735.weather_modified_app.model.beans.WeatherBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by L091735 on 26/10/2016.
 */

public interface WeatherAPI {

    @GET("{api_key}/{latitude},{longitude}")
    Observable<WeatherBean> fetchCurrentWeather(@Path("api_key") String key, @Path("latitude") double lat,
                                                @Path("longitude") double lng);
}
