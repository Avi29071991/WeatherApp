package com.avinash.weatherapp.forecastapp.kotlinDemo


/**
 * Every class can implement a companion object, which is an object that is common to all instances of that class. It’d come to be similar to static fields in Java
 */
class CompanionObjectDemo : BaseKotlinClass() {

    override fun testClass() {
        println("$TAG ${getCompanionObjectValue("from CompanionObjectDemo class")}")
    }

    companion object {
        fun getCompanionObjectValue(value: String): String {
            return "BaseKotlin Companion Object Value = $value"
        }
    }
}