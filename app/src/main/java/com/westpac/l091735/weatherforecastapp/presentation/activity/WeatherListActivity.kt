package com.westpac.l091735.weatherforecastapp.presentation.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.westpac.l091735.weatherforecastapp.R
import com.westpac.l091735.weatherforecastapp.databinding.ActivityWeatherMainBinding
import com.westpac.l091735.weatherforecastapp.di.Injectable
import com.westpac.l091735.weatherforecastapp.presentation.presenter.impl.WeatherPresenter
import com.westpac.l091735.weatherforecastapp.usecase.GetWeatherListUseCase
import com.westpac.l091735.weatherforecastapp.utils.Codes
import com.westpac.l091735.weatherforecastapp.utils.Utilities
import com.westpac.l091735.weatherforecastapp.presentation.view.WeatherView
import com.westpac.l091735.weatherforecastapp.presentation.adapter.WeatherAdapter
import com.westpac.l091735.weatherforecastapp.viewModel.DailyWeatherViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.content_weather_main.*
import kotlinx.android.synthetic.main.activity_weather_main.*

class WeatherListActivity : BaseActivity<ActivityWeatherMainBinding>(), WeatherView, Injectable,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, WeatherAdapter.OnItemClickListener {

    private lateinit var presenter: WeatherPresenter
    private lateinit var builder: GoogleApiClient.Builder
    private lateinit var locationRequest: LocationRequest
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var getWeatherUseCase: GetWeatherListUseCase
    private lateinit var inflater: LayoutInflater

    private lateinit var weatherAdapter: WeatherAdapter

    override fun onConnected(p0: Bundle?) {
        presenter.fetchLocation(googleApiClient, locationRequest)
    }

    override fun onConnectionSuspended(p0: Int) {
        googleApiClient.connect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        googleApiClient.connect()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupGoogleClientApi()
        setupPresenter()
        setRecyclerView()

        if (Utilities.checkPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            googleApiClient.connect()
        } else {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        Codes.LOCATION_PERMISSION)
            }
        }
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    fun setupUI() {
        binding.toolBarEmptyTitle = resources.getString(R.string.empty)
        binding.toolBarTitle = resources.getString(R.string.demo_name)
        setSupportActionBar(toolbar)
    }

    fun setupGoogleClientApi() {
        builder.addConnectionCallbacks(this)
        builder.addOnConnectionFailedListener(this)
        googleApiClient = builder.build()
    }

    fun setupPresenter() {
        presenter = WeatherPresenter(this, getWeatherUseCase)
    }

    fun setRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        weatherAdapter = WeatherAdapter(this, inflater)
        recycler_view.adapter = weatherAdapter
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_weather_main
    }

    override fun showProgressBar() {
        loadingbar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        loadingbar.visibility = View.GONE
    }

    override fun showErrorMessage(show: Boolean, errorMessage: String?) {
        msg_text.visibility = if (show) View.VISIBLE else View.GONE
        errorMessage?.let { msg_text.text = it }
    }

    override fun displayNetworkError() {
        msg_text.visibility = View.VISIBLE
        msg_text.text = resources.getString(R.string.no_network)
    }

    override fun showWeatherData(dataList: List<DailyWeatherViewModel>) {
        weatherAdapter.setData(dataList)
    }

    override fun showList(show: Boolean) {
        recycler_view.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == Codes.LOCATION_PERMISSION) {
            googleApiClient.connect()
        } else {
            showErrorMessage(true, resources.getString(R.string.permission_denied))
        }
    }

    override fun onRowClicked(dailyItem: DailyWeatherViewModel?) {
        //TODO need to add an activity to display the detailed view
    }

    @Inject
    fun setGoogleApiClientBuilder(builder: GoogleApiClient.Builder) {
        this.builder = builder
    }

    @Inject
    fun setLocRequest(locationRequest: LocationRequest) {
        this.locationRequest = locationRequest
    }

    @Inject
    fun setUseCase(getWeatherUseCase: GetWeatherListUseCase) {
        this.getWeatherUseCase = getWeatherUseCase
    }

    @Inject
    fun setInflater(inflater: LayoutInflater) {
        this.inflater = inflater
    }

}