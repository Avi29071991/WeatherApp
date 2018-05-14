package com.avinash.weatherapp.forecastapp.presentation.presenter.impl

import android.location.Location
import android.util.Log
import com.avinash.weatherapp.network.exception.NetworkException
import com.avinash.weatherapp.forecastapp.model.DefaultSubscriber
import com.avinash.weatherapp.forecastapp.presentation.presenter.BasePresenter
import com.avinash.weatherapp.forecastapp.usecase.GetWeatherListUseCase
import com.avinash.weatherapp.forecastapp.utils.Codes
import com.avinash.weatherapp.forecastapp.presentation.view.WeatherView
import com.avinash.weatherapp.forecastapp.model.DailyWeatherDataModel
import rx.Subscriber

class WeatherPresenter constructor(weatherView: WeatherView, weatherUseCase: GetWeatherListUseCase) : BasePresenter{

    val view: WeatherView = weatherView
    private val useCase: GetWeatherListUseCase = weatherUseCase

    override fun destroy() {
        useCase.unSubscribe()
    }

    fun dataGetWeatherDetails(location: Location?) {
        location?.let {
            useCase.setApiKey(Codes.API_KEY)
            Log.d("latitude", it.latitude.toString())
            Log.d("longitude", it.longitude.toString())
            useCase.setLatitude(it.latitude)
            useCase.setLongitude(it.longitude)
            useCase.execute(WeatherSubscriber(view) as Subscriber<Any>)
        }
    }

    class WeatherSubscriber(var view: WeatherView) : DefaultSubscriber<List<DailyWeatherDataModel>>() {
        override fun onNext(t: List<DailyWeatherDataModel>) {
            view.hideProgressBar()
            view.showErrorMessage(false, null)
            view.showWeatherData(t)
            view.showList(true)
        }

        override fun onError(e: Throwable) {
            Log.e("WeatherPresenter", e.message, e)
            view.hideProgressBar()
            view.showList(false)
            if (e is NetworkException) {
                view.displayNetworkError()
            } else {
                view.showErrorMessage(true, "Something went wrong")
            }
        }
    }
}