package com.avinash.weatherapp.forecastapp.kotlinDemo

/**
 * Kotlin objects are another element of the language that we Android developers are not familiarized with, because there is nothing like that in Java.
In fact, an object is just a data type with a single implementation. So if we want to find something similar in Java, that would be the Singleton pattern. We’ll compare them in the next section.
<p>
Singleton vs object
<p>
Singleton in Java isn’t as easy to implement as it sounds.If two threads access this singleton at a time, two instances of this object could be generated.
As you can see, you just need to use the reserved word object instead of class and the rest is the same. Just take into account that objects can’t have a constructor, as we don’t call any constructors to access to them.
The instance of the object will be created the first time we use it. So there’s a lazy instantiation here: if an object is never used, the instance will never be created.
 */
object SingletonObject {

    fun getSingleObjectText(): String {
        return "Singleton object String"
    }
}