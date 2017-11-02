package com.westpac.l091735.weatherforecastapp.presentation.presenter.impl

import android.location.Location
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.westpac.l091735.network.exception.NetworkException
import com.westpac.l091735.weatherforecastapp.model.DefaultSubscriber
import com.westpac.l091735.weatherforecastapp.presentation.presenter.BasePresenter
import com.westpac.l091735.weatherforecastapp.usecase.GetWeatherListUseCase
import com.westpac.l091735.weatherforecastapp.utils.Codes
import com.westpac.l091735.weatherforecastapp.presentation.view.WeatherView
import com.westpac.l091735.weatherforecastapp.viewModel.DailyWeatherViewModel
import rx.Subscriber

class WeatherPresenter constructor(weatherView: WeatherView, weatherUseCase: GetWeatherListUseCase) : BasePresenter, LocationListener {

    val view: WeatherView = weatherView
    val useCase: GetWeatherListUseCase = weatherUseCase

    override fun destroy() {
        useCase.unSubscribe()
    }

    override fun onLocationChanged(location: Location?) {
        location?.let { dataGetWeatherDetails(it.latitude, it.longitude) } ?: view.showErrorMessage(true, "Unable to fetch location")
    }


    fun fetchLocation(apiClient: GoogleApiClient, request: LocationRequest) {
        view.showProgressBar()
        view.showErrorMessage(false, null)
        view.showList(false)
        LocationServices.FusedLocationApi.requestLocationUpdates(
                apiClient, request, this)
    }


    fun dataGetWeatherDetails(latitude: Double, longitude: Double) {
        useCase.setApiKey(Codes.API_KEY)
        useCase.setLatitude(latitude)
        useCase.setLongitude(longitude)
        useCase.execute(WeatherSubscriber(view) as Subscriber<Any>)
    }

    class WeatherSubscriber(var view: WeatherView) : DefaultSubscriber<List<DailyWeatherViewModel>>() {
        override fun onNext(t: List<DailyWeatherViewModel>) {
            view.hideProgressBar()
            view.showErrorMessage(false, null)
            view.showWeatherData(t)
            view.showList(true)
        }

        override fun onError(e: Throwable) {
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