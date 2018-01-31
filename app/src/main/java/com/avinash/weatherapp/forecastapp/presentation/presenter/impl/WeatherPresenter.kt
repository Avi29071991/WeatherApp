package com.avinash.weatherapp.forecastapp.presentation.presenter.impl

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.avinash.weatherapp.network.exception.NetworkException
import com.avinash.weatherapp.forecastapp.model.DefaultSubscriber
import com.avinash.weatherapp.forecastapp.presentation.presenter.BasePresenter
import com.avinash.weatherapp.forecastapp.usecase.GetWeatherListUseCase
import com.avinash.weatherapp.forecastapp.utils.Codes
import com.avinash.weatherapp.forecastapp.presentation.view.WeatherView
import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModel
import rx.Subscriber

class WeatherPresenter constructor(weatherView: WeatherView, weatherUseCase: GetWeatherListUseCase) : BasePresenter, LocationListener {

    val view: WeatherView = weatherView
    val useCase: GetWeatherListUseCase = weatherUseCase
    private var googleApiClient: GoogleApiClient? = null

    fun setGoogleApiClient(client: GoogleApiClient?){
        googleApiClient = client
    }

    override fun destroy() {
        useCase.unSubscribe()
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            dataGetWeatherDetails(it.latitude, it.longitude)
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this@WeatherPresenter)
        } ?: view.showErrorMessage(true, "Unable to fetch location")
    }


    @SuppressLint("MissingPermission")
    fun fetchLocation(request: LocationRequest) {
        view.showProgressBar()
        view.showErrorMessage(false, null)
        view.showList(false)
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, request, this)
    }


    fun dataGetWeatherDetails(latitude: Double, longitude: Double) {
        useCase.setApiKey(Codes.API_KEY)
        Log.d("latitude", latitude.toString())
        Log.d("longitude", longitude.toString())
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