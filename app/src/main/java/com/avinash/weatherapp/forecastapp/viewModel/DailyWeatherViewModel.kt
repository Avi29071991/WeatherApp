package com.avinash.weatherapp.forecastapp.viewModel

import com.avinash.weatherapp.forecastapp.utils.Utilities
import com.avinash.weatherapp.model.Daily
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

    fun getDewPoint(): String {
        return Utilities.convertFarheniteToCelcius(dailyWeatherData!!.dewPoint).toString()
    }

    fun getWindSpeed(): String {
        var builder = StringBuilder(dailyWeatherData!!.windSpeed.toString())
        builder.append(" Km/hr")
        return builder.toString()
    }

    fun getHumidity(): String {
        val builder = StringBuilder((dailyWeatherData!!.humidity * 100).toString())
        builder.append("%")
        return builder.toString()
    }

    fun getOzone(): String {
        val builder = StringBuilder(dailyWeatherData!!.ozone.toString())
        builder.append("DU")
        return builder.toString()
    }

    fun getPressure(): String {
        val builder = StringBuilder(dailyWeatherData!!.pressure.toString())
        builder.append("hPa")
        return builder.toString()
    }

    fun getDailyData(): Daily? {
        return dailyWeatherData
    }

}







