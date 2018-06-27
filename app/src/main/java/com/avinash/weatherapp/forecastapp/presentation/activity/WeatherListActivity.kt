package com.avinash.weatherapp.forecastapp.presentation.activity

import android.Manifest
import android.arch.lifecycle.Observer
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.avinash.weatherapp.forecastapp.R
import com.avinash.weatherapp.forecastapp.databinding.ActivityWeatherMainBinding
import com.avinash.weatherapp.forecastapp.di.Injectable
import com.avinash.weatherapp.forecastapp.kotlinDemo.BaseKotlinClass
import com.avinash.weatherapp.forecastapp.kotlinDemo.RangeDemo
import com.avinash.weatherapp.forecastapp.presentation.presenter.impl.WeatherPresenter
import com.avinash.weatherapp.forecastapp.usecase.GetWeatherListUseCase
import com.avinash.weatherapp.forecastapp.utils.Codes
import com.avinash.weatherapp.forecastapp.utils.Utilities
import com.avinash.weatherapp.forecastapp.presentation.view.WeatherView
import com.avinash.weatherapp.forecastapp.presentation.adapter.WeatherAdapter
import com.avinash.weatherapp.forecastapp.model.DailyWeatherDataModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.content_weather_main.*
import kotlinx.android.synthetic.main.activity_weather_main.*
import com.avinash.weatherapp.forecastapp.model.CurrentLocationListener


class WeatherListActivity : BaseActivity<ActivityWeatherMainBinding>(), WeatherView, Injectable,
        WeatherAdapter.OnItemClickListener {

    private lateinit var presenter: WeatherPresenter
    private lateinit var getWeatherUseCase: GetWeatherListUseCase
    private lateinit var inflater: LayoutInflater
    private lateinit var currentLocationListener: CurrentLocationListener
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
        setupPresenter()
        setRecyclerView()

        if (Utilities.checkPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            subscribeToLocationUpdate()
        } else {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        Codes.LOCATION_PERMISSION)
            }
        }

        kotlinDemo()
    }

    private fun subscribeToLocationUpdate() {
        currentLocationListener.observe(this, Observer<Location> { location ->
            location?.let {
                Log.d("", "onChanged: location updated " + "${it.latitude} , ${it.longitude}")
                presenter.dataGetWeatherDetails(it)
            }
        })
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    private fun setupUI() {
        binding.toolBarEmptyTitle = resources.getString(R.string.empty)
        binding.toolBarTitle = resources.getString(R.string.demo_name)
        setSupportActionBar(toolbar)
    }

    private fun setupPresenter() {
        presenter = WeatherPresenter(this, getWeatherUseCase)
    }

    private fun setRecyclerView() {
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

    override fun showWeatherData(dataList: List<DailyWeatherDataModel>) {
        weatherAdapter.setData(dataList)
    }

    override fun showList(show: Boolean) {
        recycler_view.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == Codes.LOCATION_PERMISSION) {
            subscribeToLocationUpdate()
        } else {
            showErrorMessage(true, resources.getString(R.string.permission_denied))
        }
    }

    override fun onRowClicked(dailyItemData: DailyWeatherDataModel?) {
        val intent = Intent(this, WeatherDetailsActivity::class.java)
        dailyItemData?.let {
            intent.putExtra(WeatherDetailsActivity.DATA_KEY, it)
        }
        startActivity(intent)
    }

    @Inject
    fun setUseCase(getWeatherUseCase: GetWeatherListUseCase) {
        this.getWeatherUseCase = getWeatherUseCase
    }

    @Inject
    fun setInflater(inflater: LayoutInflater) {
        this.inflater = inflater
    }

    @Inject
    fun setCurrentLocationListener(listener: CurrentLocationListener) {
        this.currentLocationListener = listener
    }

    fun kotlinDemo() {
        val test: BaseKotlinClass = RangeDemo()
        //Below method is only for interface demonstration
        //if(test is NameInterface) test.setNameValue("Avinash Mandal")
        test.testClass()
        //testing Companion objects demo
        //println(test.TAG + " ${CompanionObjectDemo.getCompanionObjectValue("from WeatherList Activity")}")
        //below code is used to demonstrate the use of objects in kotlin
        /*val singletonObj = SingletonObject.getSingleObjectText()
        println("BaseKotlinClass singletonObj value = $singletonObj")*/
    }
}