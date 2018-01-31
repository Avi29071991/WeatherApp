package com.avinash.weatherapp.forecastapp.data

import android.content.Context
import javax.inject.Inject
import rx.Observable
import android.net.ConnectivityManager
import com.avinash.weatherapp.forecastapp.modelconverter.ModelConverter
import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModel
import com.avinash.weatherapp.model.Daily
import com.avinash.weatherapp.network.datasource.ApiDataSource
import com.avinash.weatherapp.network.exception.NetworkException


class DataProvider @Inject constructor(apiDataSource: ApiDataSource, modelConverter: ModelConverter, context: Context) {

    var mApiDataSource: ApiDataSource = apiDataSource
    var mModelConverter: com.avinash.weatherapp.forecastapp.modelconverter.ModelConverter = modelConverter
    var mContext: Context = context

    private fun <R> networkDependencyCall(observable: Observable<R>): Observable<R> {

        if (isDeviceOnline(mContext)) {
            return observable.onErrorResumeNext { throwable ->
                //throw error back to subscriber
                Observable.error<R>(throwable)
            }.doOnNext {
                // additional actions you need to implement if required
            }
        } else {
            return Observable.error(NetworkException(0, "Device Offline"))
        }
    }

    fun isDeviceOnline(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun fetchCurrentWeather(key: String, lat: Double, lng: Double): Observable<List<Daily>?> {
        return networkDependencyCall(mApiDataSource.fetchCurrentWeather(key, lat, lng)?.map {
            it?.let {
                mModelConverter.dailyDataList(it)
            }
        } as Observable<List<Daily>?>)
    }

    fun processWeatherList(list: List<Daily>?): Observable<List<DailyWeatherViewModel>> {
        return Observable.create({ subscriber ->
            val dailyList = ArrayList<DailyWeatherViewModel>()
            if (list != null) {
                list.mapTo(dailyList) { DailyWeatherViewModel(it) }
                subscriber.onNext(dailyList)
            } else {
                subscriber.onError(Exception())
            }
            subscriber.onCompleted()
        })
    }
}