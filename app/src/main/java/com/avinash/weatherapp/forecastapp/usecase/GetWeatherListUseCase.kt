package com.avinash.weatherapp.forecastapp.usecase

import com.avinash.weatherapp.forecastapp.data.DataProvider
import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModel
import rx.Observable
import javax.inject.Inject

class GetWeatherListUseCase @Inject constructor(dataProvider: DataProvider) : BaseUseCase() {

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var apiKey: String
    private val provider: DataProvider = dataProvider

    override fun buildUseCaseObservable(): Observable<List<DailyWeatherViewModel>> {
        return provider.fetchCurrentWeather(apiKey, latitude, longitude)
                .flatMap { provider.processWeatherList(it) }
    }

    fun setLatitude(lat: Double) {
        this.latitude = lat
    }

    fun setLongitude(lng: Double) {
        this.longitude = lng
    }

    fun setApiKey(key: String) {
        this.apiKey = key
    }

}