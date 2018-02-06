package com.avinash.weatherapp.forecastapp.kotlinDemo

class ControlFlows : BaseKotlinClass() {


    val a = 10
    val b = 20

    /**
     * Method to demonstrate if control as traditional method
     */
    private fun traditional(): Int {
        if (a < b) {
            return a
        } else {
            return b
        }
    }

    /**
     * Method to demonstrate if control as expression
     */
    private fun expression(): Int {
        return if (a > b) a else b
    }

    /**
     * Method to demonstrate when control
     */
    private fun findMax() {
        val max = expression()
        when (max) {
            a -> println(TAG + " Max value is = " + a)
            b -> println(TAG + " Max value is = " + b)
            else -> {
                println(TAG + " Max value is neither 'a' nor 'b'")
            }
        }
    }

    private fun forDemo() {
        println(TAG + " Demonstrating For loop..")
        for (value in a..b) {
            println(TAG + " " + value)
        }
    }

    override fun testClass() {
        val traditional = traditional()
        println(TAG + " Value of traditional = " + traditional)

        val expression = expression()
        println(TAG + " Value of expression = " + expression)

        findMax()
        forDemo()
    }
}