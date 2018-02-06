package com.avinash.weatherapp.forecastapp.kotlinDemo

/**
 * Class used to demonstrate the use of constructors , properties and fields(variables and getters setters , interfaces)
 */
class ClassesConstructorDemo(value: String) : BaseKotlinConstructorClass(value), NameInterface, MyInterface {

    var name: String? = null
    lateinit var lateName: String
    internal val number = 6

    /**
     * Implementations for properties and fields
     */
    //public , protected , private , internal
    /*
    get(){ return this.toString()}
    set(item) {
             field = item
    }*/

    /**
     * Implementation for Constructors and its initialization
     */
    init {
        name = "{From init : $value}"
        println(TAG + " Value of argument from constructor initializer = " + name)
    }


    override fun testClass() {
        println(TAG + " Value of argument = " + name)
        lateName = "late initialized : $name"
        println(TAG + " Value of argument late initialized = " + lateName)
    }

    /**
     * Implementation for interfaces and its implementation
     */
    override fun setNameValue(nameValue: String) {
        super<NameInterface>.setNameValue(nameValue)
        super<MyInterface>.setNameValue(nameValue)
        this.name = nameValue
    }

    /**
     * Implementation for inner classes
     */
    inner class Demo {
        val num = number
    }
}