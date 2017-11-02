package com.westpac.l091735.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger


/**
 * Created by L091735 on 27/10/2017.
 */
class ServiceInterceptor : Interceptor {

    private val sRfc1123TimestampFormat = getRfc1123TimestampFormat()

    private fun getRfc1123TimestampFormat(): SimpleDateFormat {
        return SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")
    }

    fun parseRfc1123Timestamp(timestamp: String?, offset: String?): Date? {
        if (timestamp != null) {
            try {
                synchronized(sRfc1123TimestampFormat) {
                    var date = sRfc1123TimestampFormat.parse(timestamp)
                    if (offset != null) {
                        date = Date(date.getTime() + java.lang.Long.parseLong(offset) * 1000)
                    }
                    return date
                }
            } catch (e: ParseException) {
                Logger.getGlobal().log(Level.SEVERE, "parseRfc1123Timestamp: %s", e.toString())
            } catch (e: NumberFormatException) {
                Logger.getGlobal().log(Level.SEVERE, "parseRfc1123Timestamp: %s", e.toString())
            }

        }
        return null
    }

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val response = chain?.proceed(chain.request())
        parseRfc1123Timestamp(response?.header("Date"), response?.header("Age"))
        return response?.let { it }
    }
}