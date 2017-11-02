package com.westpac.l091735.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by L091735 on 27/10/2017.
 */
class WeatherMain {

    @SerializedName("daily")
    @Expose
    private var daily: DailyMain? = null


    fun getDaily(): DailyMain? {
        return daily
    }

    fun setDaily(daily: DailyMain) {
        this.daily = daily
    }
}