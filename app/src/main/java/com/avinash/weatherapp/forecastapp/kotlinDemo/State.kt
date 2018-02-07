package com.avinash.weatherapp.forecastapp.kotlinDemo

/**
 * Created by Avinash Mandal on 6/02/2018.
 */
enum class State(value: String) {

    STATE_ACTIVE("Active"),
    STATE_INACTIVE("Inactive");

    var stateValue: String? = value

   /* STATE_ACTIVE {
        override fun signal() = STATE_INACTIVE

    },

    STATE_INACTIVE {
        override fun signal() = STATE_ACTIVE
    };

    abstract fun signal(): State*/

}
