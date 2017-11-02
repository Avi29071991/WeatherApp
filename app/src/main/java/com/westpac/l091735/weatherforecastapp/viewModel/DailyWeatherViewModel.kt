package com.westpac.l091735.weatherforecastapp.viewModel

import com.westpac.l091735.model.Daily
import com.westpac.l091735.weatherforecastapp.utils.Utilities
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class DailyWeatherViewModel constructor(dailyWeather: Daily) : Serializable {

    var dailyWeatherData: Daily? = dailyWeather

    fun getIcon(): String? {
        return dailyWeatherData!!.icon
    }

    fun getSummary(): String? {
        return dailyWeatherData!!.summary
    }

    fun getDate(): String {
        val date = Date(dailyWeatherData!!.time * 1000)
        val df2 = SimpleDateFormat("EEE dd/MM/yyyy", Locale.US)
        val dateText = df2.format(date)
        if (Utilities.isNotEmpty(dateText)) {
            val s = dateText.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            s.indices
                    .filter { it == 1 }
                    .forEach { return s[it] }
        }
        return ""
    }

    fun getDay(): String {
        val date = Date(dailyWeatherData!!.time * 1000)
        val df2 = SimpleDateFormat("EEE dd/MM/yyyy", Locale.US)
        val dateText = df2.format(date)
        if (Utilities.isNotEmpty(dateText)) {
            val s = dateText.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            s.indices
                    .filter { it == 0 }
                    .forEach { return s[it] }
        }
        return ""
    }

    fun getMaxTemperature(): String {
        return Utilities.convertFarheniteToCelcius(dailyWeatherData!!.temperatureMax).toString()
    }

    fun getMinTemperature(): String {
        return Utilities.convertFarheniteToCelcius(dailyWeatherData!!.temperatureMin).toString()
    }

}







