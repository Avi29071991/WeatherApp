package com.example.l091735.weather_modified_app.model.interfaces;


import com.example.l091735.weather_modified_app.model.beans.DailyData;

import java.util.List;

public interface IWeather {


    /**
     * Specifies the list of data which needs to be displayed when it is fetched successfully from API
     *
     * @param dataList list of weather forecast data for 8 days
     **/
    void onWeatherDataResponseSuccessful(List<DailyData> dataList);

    /**
     * Specifies the error message which needs to be displayed when there is an error while fetching data from API
     *
     * @param message list of weather forecast data for 8 days
     **/
    void onWeatherDataResponseFailure(String message);
}
