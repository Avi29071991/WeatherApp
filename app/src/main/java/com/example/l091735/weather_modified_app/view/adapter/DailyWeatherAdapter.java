package com.example.l091735.weather_modified_app.view.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l091735.weather_modified_app.R;
import com.example.l091735.weather_modified_app.databinding.WeatherRowBinding;
import com.example.l091735.weather_modified_app.model.beans.DailyData;
import com.example.l091735.weather_modified_app.utils.Utilities;
import com.example.l091735.weather_modified_app.utils.WeatherConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by L091735 on 26/10/2016.
 */

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.SimpleViewHolder> {

    private List<DailyData> beanList;
    private static Context context;

    public DailyWeatherAdapter(List<DailyData> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_row, parent, false);
        return new SimpleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        final DailyData data = beanList.get(position);
        if (data != null) {
            holder.getRowBinding().setDailyData(data);
        }
    }

    @Override
    public int getItemCount() {
        if (beanList != null && beanList.size() > 0) {
            return beanList.size();
        }
        return 0;
    }


    /****
     * Creating different binding adapters for displaying
     * date , time , temperature and weather icon directly
     * using custom tags in layout xml files.
     ****/


    @BindingAdapter({"app:dateTime"})
    public static void setDate(TextView textView, long dateTimeInMillis) {
        if (textView != null && dateTimeInMillis != 0) {
            Date date = new Date(dateTimeInMillis * 1000);
            SimpleDateFormat df2 = new SimpleDateFormat("EEE dd/MM/yyyy", Locale.US);
            String dateText = df2.format(date);
            if (Utilities.isNotEmpty(dateText)) {
                String s[] = dateText.split("\\s");
                for (int x = 0; x < s.length; x++) {
                    if (x == 0 && textView.getId() == R.id.tvDay) {
                        textView.setText(s[x]);
                    } else if (x == 1 && textView.getId() == R.id.tvDate) {
                        textView.setText(s[x]);
                    }
                }
            }
        }
    }

    @BindingAdapter("app:temperature")
    public static void setTemparature(TextView textview, double temp) {
        if (textview != null) {
            textview.setText(context.getResources().getString(R.string.degcelcius, String.valueOf(Utilities.convertFarheniteToCelcius(temp))));
        }
    }

    @BindingAdapter("app:text")
    public static void setText(TextView textView, String text) {
        if (textView != null && Utilities.isNotEmpty(text)) {
            textView.setText(text);
        } else {
            textView.setText(context.getResources().getString(R.string.not_available));
        }
    }

    @BindingAdapter("app:weatherIcon")
    public static void setWeatherIcon(ImageView imageView, String iconType) {
        if (imageView != null && Utilities.isNotEmpty(iconType)) {
            switch (iconType) {
                case WeatherConstants.RAIN:
                case WeatherConstants.HAIL:
                case WeatherConstants.THUNDERSTORM:

                    imageView.setImageResource(R.drawable.w_09n);
                    break;

                case WeatherConstants.CLEAR_DAY:
                case WeatherConstants.CLEAR_NIGHT:
                case WeatherConstants.SNOW:
                case WeatherConstants.WIND:
                case WeatherConstants.SLEET:

                    imageView.setImageResource(R.drawable.w_01d);
                    break;

                case WeatherConstants.CLOUDY:
                case WeatherConstants.FOG:
                case WeatherConstants.PARTLY_CLOUDY_DAY:
                case WeatherConstants.PARTLY_CLOUDY_NIGHT:

                    imageView.setImageResource(R.drawable.w_03d);
                    break;
            }
        }
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {

        private WeatherRowBinding rowBinding;

        public WeatherRowBinding getRowBinding() {
            return rowBinding;
        }

        public SimpleViewHolder(View itemView) {
            super(itemView);
            rowBinding = WeatherRowBinding.bind(itemView);
        }
    }
}
