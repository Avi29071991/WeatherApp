package com.avinash.weatherapp.forecastapp.presentation.view

interface BaseView {

    fun showProgressBar()

    fun hideProgressBar()

    fun showErrorMessage(show: Boolean, errorMessage: String?)

}