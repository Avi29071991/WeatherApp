package com.avinash.weatherapp.forecastapp.kotlinDemo

import kotlin.properties.Delegates

/**
 * Class to demonstrate usage of Delegated properties like "lazy" ,  "Observable , etc".
 * There are many such Delegated properties in Collections Data type for sorting , mapping , etc.. which can also be used
 */
class DelegatedProperties : BaseKotlinClass() {
    override fun testClass() {
        println("$TAG demo for Lazy delegated property")
        lazyDemo()
        println("$TAG demo for Observable delegated property")
        observableDemo()
    }

    /**
     * Method to demonstrate usage of Observable delegate property in kotlin
     */
    private fun lazyDemo() {
        val lazyString: String by lazy {
            print("$TAG Computed value :")
            "Lazy String"
        }

        println(lazyString)
        println("$TAG $lazyString")
    }

    /**
     * Method to demonstrate usage of Observable delegate property in kotlin
     */
    private fun observableDemo() {
        var name: String by Delegates.observable("just initialized") { property, oldValue, newValue ->
            println("$TAG $oldValue , $newValue")
        }
        name = "First Modified"
        name = "Second Modified"
    }
}