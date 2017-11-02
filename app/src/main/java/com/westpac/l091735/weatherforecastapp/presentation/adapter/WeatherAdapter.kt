package com.westpac.l091735.weatherforecastapp.presentation.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.westpac.l091735.weatherforecastapp.BR
import com.westpac.l091735.weatherforecastapp.R
import com.westpac.l091735.weatherforecastapp.databinding.WeatherRowBinding
import com.westpac.l091735.weatherforecastapp.viewModel.DailyWeatherViewModel


class WeatherAdapter constructor(itemClickListener: OnItemClickListener, layoutInflater: LayoutInflater) : RecyclerView.Adapter<WeatherAdapter.DailyViewHolder>() {

    var listener: OnItemClickListener = itemClickListener
    var dailyList = ArrayList<DailyWeatherViewModel>()
    var inflater: LayoutInflater = layoutInflater

    override fun onCreateViewHolder(viewGroup: ViewGroup?, i: Int): DailyViewHolder {
        val binding: WeatherRowBinding = DataBindingUtil.inflate(inflater, R.layout.weather_row, viewGroup, false)
        return DailyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    override fun onBindViewHolder(viewHolder: DailyViewHolder?, position: Int) {
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


    inner class DailyViewHolder internal constructor(binding: WeatherRowBinding, listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var itemListener: OnItemClickListener = listener
        var dataBinding: WeatherRowBinding = binding

        init {
            dataBinding.container.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            itemListener.onRowClicked(getItem(adapterPosition))
        }
    }

    fun getItem(position: Int): DailyWeatherViewModel? {
        if (!dailyList.isEmpty()) return dailyList[position] else return null
    }
}