package com.avinash.weatherapp.forecastapp.kotlinDemo

class ReturnJumps : BaseKotlinClass() {

    /**
     * Method for demonstrating break function
     */
    private fun breakDemo() {
        loop@ for (i in 10..15) {
            println(TAG + " Value of I = " + i)
            for (j in 1..2) {
                if (i == 13) {
                    println(TAG + " break called since I==13")
                    break@loop
                }
                println(TAG + " Value of J = " + j)
            }

        }
    }

    /**
     * Method for demonstrating continue function
     */
    private fun continueDemo() {
        loop@ for (i in 10..15) {
            println(TAG + " Value of I = " + i)
            for (j in 1..2) {
                if (i == 13) {
                    println(TAG + " continue called since I==13")
                    continue@loop
                }
                println(TAG + " Value of J = " + j)
            }

        }
    }

    /**
     * Method for demonstrating non-local return directly to the caller of nonLocalReturn()
     */
    fun nonLocalReturn() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // non-local return directly to the caller of nonLocalReturn()
            println(TAG + " " + it)
        }
        println(TAG + " this point is unreachable")
    }

    /**
     * Method for demonstrating local return to the caller of the lambda, i.e. the forEach loop
     */
    fun localReturn() {
        listOf(1, 2, 3, 4, 5).forEach lit@ {
            if (it == 3) return@lit // local return to the caller of the lambda, i.e. the forEach loop
            println(TAG + " " + it)
        }
        println(TAG + " done with explicit label")
    }

    /**
     * Method for demonstrating local return to the caller of the lambda, i.e. the forEach loop
     */
    fun forEachReturn() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // local return to the caller of the lambda, i.e. the forEach loop
            println(TAG + " " + it)
        }
        println(TAG + " done with implicit label")
    }

    /**
     * Method for demonstrating local return to the caller of the anonymous fun, i.e. the forEach loop
     */
    fun anonymousReturn() {
        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return  // local return to the caller of the anonymous fun, i.e. the forEach loop
            println(TAG + " " + value)
        })
        println(TAG + " done with anonymous function")
    }

    /**
     * Method for demonstrating  non-local return from the lambda passed to run
     */
    fun runReturn() {
        run loop@ {
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // non-local return from the lambda passed to run
                println(TAG + " " + it)
            }
        }
        println(TAG + " done with run function")
    }

    override fun testClass() {
        println(TAG + " Demo for Break..")
        breakDemo()
        println(TAG + " Demo for Continue")
        continueDemo()
        println(TAG + " Demo for Return Non-Local..")
        nonLocalReturn()
        println(TAG + " Demo for Return Local")
        localReturn()
        println(TAG + " Demo for Return For Each..")
        forEachReturn()
        println(TAG + " Demo for Return Anonymous")
        anonymousReturn()
        println(TAG + " Demo for Return Run..")
        runReturn()
    }
}