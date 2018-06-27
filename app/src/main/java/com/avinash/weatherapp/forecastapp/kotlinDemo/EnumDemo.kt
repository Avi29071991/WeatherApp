package com.avinash.weatherapp.forecastapp.kotlinDemo

/**
 * Created by Avinash Mandal on 6/02/2018.
 */
class EnumDemo : BaseKotlinClass() {
    /**
     * Demonstrating the use of Enumerations
     */
    override fun testClass() {
        println(TAG + " Active state value : ${State.STATE_ACTIVE.stateValue}")
        println(TAG + " Inactive state value : ${State.STATE_INACTIVE.stateValue}")
    }
}
