package com.example.l091735.weather_modified_app.model.interfaces;

import com.example.l091735.weather_modified_app.model.beans.WeatherBean;

import dagger.Module;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by L091735 on 26/10/2016.
 */


public interface WeatherAPI {

    /**
     * Returns the Observable API using retrofit for fetching the current weather data depending upon the latitude and longitude provided.
     *
     * @param key API Key for calling forecast.io API.
     * @param lat latitude of the location.
     * @param lng longitude of the location.
     **/
    @GET("{api_key}/{latitude},{longitude}")
    Observable<WeatherBean> fetchCurrentWeather(@Path("api_key") String key, @Path("latitude") double lat,
                                                @Path("longitude") double lng);


    /**
     * Returns the General API using retrofit for fetching the current weather data depending upon the latitude and longitude provided.
     *
     * @param key API Key for calling forecast.io API.
     * @param lat latitude of the location.
     * @param lng longitude of the location.
     **/
    @GET("{api_key}/{latitude},{longitude}")
    Call<WeatherBean> fetchCurrentWeatherBean(@Path("api_key") String key, @Path("latitude") double lat,
                                              @Path("longitude") double lng);
}
