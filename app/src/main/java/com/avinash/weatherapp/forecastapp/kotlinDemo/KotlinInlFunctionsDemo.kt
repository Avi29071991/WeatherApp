package com.avinash.weatherapp.forecastapp.kotlinDemo

import android.util.Log

class KotlinInlFunctionsDemo : BaseKotlinClass() {

    override fun testClass() {
        println("$TAG APPLY, ALSO, LET")
        executeAlsoLetFunction()
        println("$TAG WITH")
        executeWithFunction()
        println("$TAG RUN")
        executeRunFunction()
    }

    /**
     * Method to show usage of "apply" function
     * 1. functional literal with receiver
     * 2. called object is returned
     * typical usage -> to Initialize or configure an object
     */
    private fun getHuman(): Human? {
        return Human().apply {
            name = "Avinash"
            hobby = "Cricket"
            age = 27
        }
    }

    /**
     * Method to show usage of "also" and "let" function
     * "also"
     * 1. called object passed in via argument
     * 2. called object is returned
     * typical usage -> side effects in chains
     *
     * "let"
     * called object passed in via argument
     * return type is whatever the lambda returns
     * typical usages -> convert from one type to another and handling Nullability
     */
    private fun executeAlsoLetFunction() {
        //Old way - transforming data with intermediary variable
        println("$TAG old way of getting human data and using logs to print its name")
        val human = getHuman()
        human?.name?.let { Log.d("$TAG Human name", it) }
        //New way - use 'also' to stay in the method chains
        println("$TAG new way of getting human data and using logs to print its name")
        getHuman()?.also { it -> it.name?.let { Log.d("$TAG Human name", it) } }
    }

    /**
     * Method to show usage of "with" function
     * 1. not an extension function
     * 2. the relevant object is passed in as an argument
     * 3. returns whatever the lambda returns
     * typical usage -> logically group calls on an object
     */
    private fun executeWithFunction() {
        val returnJumps = ReturnJumps()
        with(returnJumps) {
            localReturn()
            forEachReturn()
        }
    }

    /**
     * Method to show usage of "run" function
     * 1. lambda with a receiver
     * 2. return type is whatever the lambda returns
     * 3. there is also a non-extension version of this function
     * typical usage -> same as "let" except allows receiver access
     */
    private fun executeRunFunction() {
        val returnJumps = ReturnJumps()
        returnJumps.run {
            anonymousReturn()
            runReturn()
        }
    }
}