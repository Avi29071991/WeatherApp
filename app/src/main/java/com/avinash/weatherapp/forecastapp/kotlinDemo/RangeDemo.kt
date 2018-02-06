package com.avinash.weatherapp.forecastapp.kotlinDemo

class RangeDemo : BaseKotlinClass() {
    override fun testClass() {
        normalRange()
        reversedRange()
        reversedRangeWithStep()
        rangeFirstLastElements()
    }

    private fun normalRange() {
        println("$TAG normal range demo")
        for (i in 1..10) {
            if (i == 1) {
                print("$TAG ")
            }
            print("$i ")
        }
    }

    private fun reversedRange() {
        println("$TAG reversed range demo")
        for (i in 10 downTo 1) {
            if (i == 10) {
                print("$TAG ")
            }
            print("$i ")
        }
    }

    private fun reversedRangeWithStep() {
        println("$TAG reversed range demo")
        for (i in 10 downTo 1 step 2) {
            if (i == 10) {
                print("$TAG ")
            }
            print("$i ")
        }
    }

    private fun rangeFirstLastElements() {
        println("$TAG range first and last demo")
        val rangeObj = (1..12 step 3)
        println("$TAG last item = ${rangeObj.last} , first item = ${rangeObj.first}")
    }
}