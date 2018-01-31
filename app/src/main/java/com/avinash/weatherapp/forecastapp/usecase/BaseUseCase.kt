package com.avinash.weatherapp.forecastapp.usecase

import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.Subscriptions

abstract class BaseUseCase {
    private var subscription = Subscriptions.empty()
    protected abstract fun buildUseCaseObservable(): Observable<*>

    fun execute(useCaseSubscriber: Subscriber<Any>) {
        this.subscription = this.buildUseCaseObservable().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(useCaseSubscriber)
    }

    fun unSubscribe() {
        if (!this.subscription.isUnsubscribed) {
            this.subscription.unsubscribe()
        }
    }
}