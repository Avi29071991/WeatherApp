package com.avinash.weatherapp.forecastapp.kotlinDemo

/**
 * Class used to demonstrate usage of Data classes along with copy() method and destructed declaration for Data class
 */
class DataClassDemo : BaseKotlinClass() {

    private fun dataDemo() {
        println(TAG + " Initialized Data Class: ")
        val avinash = User("Avinash", 27)
        println(TAG + " Name : ${avinash.name} , Age : ${avinash.age}")
        val updateUser = avinash.copy(name = "Avinash Mandal", age = 26)
        val (userName, userAge) = updateUser
        println("$TAG Updated Name : $userName , Age : $userAge")
    }

    override fun testClass() {
        dataDemo()
    }
}