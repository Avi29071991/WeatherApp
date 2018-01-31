package com.avinash.weatherapp.forecastapp.model

import rx.Subscriber

/**
 * Created by L091735 on 1/11/2017.
 */
open class DefaultSubscriber<T> : Subscriber<T>() {

    override fun onCompleted() {}

    override fun onError(e: Throwable) {}

    override fun onNext(t: T) {}
}