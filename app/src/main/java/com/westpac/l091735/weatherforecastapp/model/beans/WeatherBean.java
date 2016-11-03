package com.westpac.l091735.weatherforecastapp.model.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rx.Observable;


public class WeatherBean {


    @SerializedName("daily")
    @Expose
    private DailyMain daily;


    public DailyMain getDaily() {
        return daily;
    }

    public void setDaily(DailyMain daily) {
        this.daily = daily;
    }

    public Observable filterWebService() {
        if (daily != null && daily.getData() != null && daily.getData().size() > 0) {
            return Observable.just(this);
        } else {
            return Observable.error(
                    new Exception("There was a problem fetching the weather data."));
        }
    }


}
