package com.avinash.weatherapp.forecastapp.model

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.LocationListener
import com.google.android.gms.common.api.GoogleApiClient
import javax.inject.Inject


/**
 * Class responsible for fetching and listening to location changes.
 */
class CurrentLocationListener @Inject constructor(context: Context) : LiveData<Location>(), LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    lateinit var googleApiClient: GoogleApiClient
    private val mContext: Context = context
    val TAG = "CurrentLocationListener"

    init {
        buildGoogleApiClient()
    }

    /**
     * Overridden method from LiveData class to check if it is necessary to start connect() of googleApiClient
     */
    override fun onActive() {
        googleApiClient.connect()
    }

    /**
     * Overridden method from LiveData class to check if it is necessary to start disconnect() of googleApiClient and remove location updates
     */
    override fun onInactive() {
        if (googleApiClient.isConnected) {
            googleApiClient.let {
                LocationServices.FusedLocationApi.removeLocationUpdates(
                        it, this)
            }
        }
        googleApiClient.disconnect()
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(connectionHint: Bundle?) {
        Log.d(TAG, "connected to google api client")
        googleApiClient.let {
            val lastLocation = LocationServices.FusedLocationApi.getLastLocation(it)

            if (lastLocation != null) {
                setValue(lastLocation)
            } else {
                Log.e(TAG, "onConnected: last location value is NULL")
            }

            if (hasActiveObservers() && it.isConnected) {
                val locationRequest = LocationRequest.create()
                LocationServices.FusedLocationApi.requestLocationUpdates(it, locationRequest, this)
            }
        }

    }

    override fun onLocationChanged(location: Location) {
        Log.d(TAG, "Location changed received: " + location)
        value = location
    }

    override fun onConnectionSuspended(cause: Int) {
        Log.w(TAG, "On Connection suspended " + cause)
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        Log.e(TAG, "GoogleApiClient connection has failed " + result)
    }

    /**
     * Method for initializing googleApiClient object
     */
    private fun buildGoogleApiClient() {
        Log.d(TAG, "Build google api client")
        googleApiClient = GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
    }

}