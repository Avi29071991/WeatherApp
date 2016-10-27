package com.example.l091735.weather_modified_app.model.interfaces;


import com.example.l091735.weather_modified_app.model.beans.DailyData;

import java.util.List;

public interface IWeather {

    void onWeatherDataResponseSuccessful(List<DailyData> dataList);

    void onWeatherDataResponseFailure(String message);
}
