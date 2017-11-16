package com.westpac.l091735.weatherforecastapp.modelconverter

import com.westpac.l091735.model.Daily
import com.westpac.l091735.network.model.DailyData

class ModelConverter {

    fun dailyDataList(dailyData: List<DailyData>): List<Daily> {
        //Just for test
        var list: ArrayList<Daily> = ArrayList()
        dailyData.mapTo(list) {
            Daily.Builder()
                    .time(it.getTime())
                    .summary(it.getSummary() as String)
                    .icon(it.getIcon() as String)
                    .sunriseTime(it.getSunriseTime())
                    .sunsetTime(it.getSunsetTime())
                    .temperatureMin(it.getTemperatureMin())
                    .temperatureMinTime(it.getTemperatureMinTime())
                    .temperatureMax(it.getTemperatureMax())
                    .temperatureMaxTime(it.getTemperatureMaxTime())
                    .dewPoint(it.getDewPoint())
                    .humidity(it.getHumidity())
                    .windSpeed(it.getWindSpeed())
                    .pressure(it.getPressure())
                    .ozone(it.getOzone())
                    .build()
        }

        return list
    }
}