package com.avinash.weatherapp.forecastapp.presentation.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avinash.weatherapp.forecastapp.BR
import com.avinash.weatherapp.forecastapp.R
import com.avinash.weatherapp.forecastapp.databinding.WeatherRowBinding
import com.avinash.weatherapp.forecastapp.viewModel.DailyWeatherViewModel


class WeatherAdapter constructor(itemClickListener: WeatherAdapter.OnItemClickListener, layoutInflater: LayoutInflater) : RecyclerView.Adapter<WeatherAdapter.DailyViewHolder>() {

    var listener: WeatherAdapter.OnItemClickListener = itemClickListener
    var dailyList = ArrayList<DailyWeatherViewModel>()
    var inflater: LayoutInflater = layoutInflater

    override fun onCreateViewHolder(viewGroup: ViewGroup?, i: Int): WeatherAdapter.DailyViewHolder {
        val binding: WeatherRowBinding = DataBindingUtil.inflate(inflater, R.layout.weather_row, viewGroup, false)
        return DailyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    override fun onBindViewHolder(viewHolder: WeatherAdapter.DailyViewHolder?, position: Int) {
        val weatherBinding: WeatherRowBinding = viewHolder?.dataBinding as WeatherRowBinding
        weatherBinding.setVariable(BR.dailyData,getItem(position))
    }

    fun setData(dataList: List<DailyWeatherViewModel>) {
        dailyList.clear()
        for (data in dataList) {
            dailyList.add(data)
        }
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onRowClicked(dailyItem: DailyWeatherViewModel?)
    }


    inner class DailyViewHolder internal constructor(binding: WeatherRowBinding, listener: WeatherAdapter.OnItemClickListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var itemListener: WeatherAdapter.OnItemClickListener = listener
        var dataBinding: WeatherRowBinding = binding

        init {
            dataBinding.container.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            itemListener.onRowClicked(getItem(adapterPosition))
        }
    }

    fun getItem(position: Int): DailyWeatherViewModel? {
        return if (!dailyList.isEmpty()) dailyList[position] else null
    }
}