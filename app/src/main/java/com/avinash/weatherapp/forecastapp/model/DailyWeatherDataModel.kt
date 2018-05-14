package com.avinash.weatherapp.forecastapp.model

import com.avinash.weatherapp.forecastapp.utils.Utilities
import com.avinash.weatherapp.model.Daily
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class DailyWeatherDataModel constructor(dailyWeather: Daily) : Serializable {

    var dailyWeatherData: Daily? = dailyWeather

    fun getIcon(): String? {
        return getDailyData()?.icon ?: run { "" }
    }

    fun getSummary(): String? {
        return getDailyData()?.summary ?: run { "" }
    }

    fun getDate(): String {
        getDailyData()?.time?.let {
            val date = Date(it * 1000)
            val df2 = SimpleDateFormat("EEE dd/MM/yyyy", Locale.US)
            val dateText = df2.format(date)
            if (Utilities.isNotEmpty(dateText)) {
                val s = dateText.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                s.indices
                        .filter { it == 1 }
                        .forEach { return s[it] }
            }
        }
        return ""
    }

    fun getDay(): String {
        getDailyData()?.time?.let {
            val date = Date(it * 1000)
            val df2 = SimpleDateFormat("EEE dd/MM/yyyy", Locale.US)
            val dateText = df2.format(date)
            if (Utilities.isNotEmpty(dateText)) {
                val s = dateText.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                s.indices
                        .filter { it == 0 }
                        .forEach { return s[it] }
            }
        }
        return ""
    }

    fun getMaxTemperature(): String {
        return getDailyData()?.temperatureMax?.let { Utilities.convertFarheniteToCelcius(it).toString() } ?: run { "" }
    }

    fun getMinTemperature(): String {
        return getDailyData()?.temperatureMin?.let { Utilities.convertFarheniteToCelcius(it).toString() } ?: run { "" }
    }

    fun getDewPoint(): String {
        return getDailyData()?.dewPoint?.let { Utilities.convertFarheniteToCelcius(it).toString() } ?: run { "" }
    }

    fun getWindSpeed(): String {
        return getDailyData()?.windSpeed?.let {
            val builder = StringBuilder(it.toString())
            builder.append(" Km/hr")
            builder.toString()
        } ?: run { "" }
    }

    fun getHumidity(): String {
        return getDailyData()?.humidity?.let {
            val builder = StringBuilder(it.toString())
            builder.append("%")
            builder.toString()
        } ?: run { "" }
    }

    fun getOzone(): String {
        return getDailyData()?.ozone?.let {
            val builder = StringBuilder(it.toString())
            builder.append("DU")
            builder.toString()
        } ?: run { "" }
    }

    fun getPressure(): String {
        return getDailyData()?.pressure?.let {
            val builder = StringBuilder(it.toString())
            builder.append("hPa")
            builder.toString()
        } ?: run { "" }
    }

    fun getDailyData(): Daily? {
        return dailyWeatherData
    }

}







