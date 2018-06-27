package com.avinash.weatherapp.forecastapp.kotlinDemo

class DelegationDemo : BaseKotlinClass() {

    override fun testClass() {
        val base = BaseImplementation(20)
        println("$TAG ${DerivedClass(base).writeValue()}")
    }

    /**
     * The by-clause in the supertype list for Derived indicates that "base" will be stored internally in objects of "Derived"
     * and the compiler will generate all the methods of "Base" that forward to "base".
     */
    class DerivedClass(base: Base) : Base by base

    class BaseImplementation(private val x: Int) : Base {
        override fun writeValue(): String {
            return "Value is $x"
        }
    }

    interface Base {
        fun writeValue(): String
    }
}