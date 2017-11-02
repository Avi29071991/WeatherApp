package com.westpac.l091735.weatherforecastapp.presentation.view

interface BaseView {

    fun showProgressBar()

    fun hideProgressBar()

    fun showErrorMessage(show: Boolean, errorMessage: String?)

}