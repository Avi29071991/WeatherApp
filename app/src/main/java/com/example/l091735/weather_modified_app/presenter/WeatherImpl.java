package com.example.l091735.weather_modified_app.presenter;

import android.content.Context;


import com.example.l091735.weather_modified_app.application.MyWeatherApplication;
import com.example.l091735.weather_modified_app.model.beans.DailyData;
import com.example.l091735.weather_modified_app.model.beans.WeatherBean;
import com.example.l091735.weather_modified_app.model.interfaces.IWeather;
import com.example.l091735.weather_modified_app.model.interfaces.WeatherAPI;
import com.example.l091735.weather_modified_app.utils.Codes;
import com.example.l091735.weather_modified_app.utils.Utilities;

import com.example.l091735.weather_modified_app.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class WeatherImpl {

    private Context context;

    @Inject
    WeatherAPI apiService;

    @Inject
    Retrofit retrofitObj;

    public WeatherImpl(Context context) {
        this.context = context;
        ((MyWeatherApplication) context).getAppComponent().injectPojo(this);
    }

    /*****
     * Creating an observable for fethcing the weather data from Forecast API
     *****/
    private Observable<WeatherBean> callWeatherAPI(double latitude, double longitude) {
        if (Utilities.isAlive(context)) {

            apiService = retrofitObj.create(WeatherAPI.class);

            Observable<WeatherBean> weatherBeanObservable = apiService.fetchCurrentWeather(Codes.API_KEY, latitude, longitude);
            weatherBeanObservable.flatMap(new Func1<WeatherBean, Observable<? extends WeatherBean>>() {
                @Override
                public Observable<? extends WeatherBean> call(WeatherBean weatherBean) {
                    return weatherBean.filterWebService();
                }
            });

            return weatherBeanObservable;

        }

        return null;
    }

    /****
     * Creating a subscription for getting detais from the observer for weather API and display details accordingly.
     ****/

    public void fetchWeatherDataForDisplay(final IWeather iWeatherListner, final double latitude, final double longitude) {

        if (Utilities.isOnline(context)) {

            final List<DailyData> dataList = new ArrayList<>();
            dataList.clear();

            callWeatherAPI(latitude, longitude)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<WeatherBean>() {
                        @Override
                        public void onCompleted() {
                            if (iWeatherListner != null) {
                                if (dataList != null && dataList.size() > 0) {
                                    iWeatherListner.onWeatherDataResponseSuccessful(dataList);
                                } else {
                                    iWeatherListner.onWeatherDataResponseFailure(context.getResources().getString(R.string.no_data));
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            if (iWeatherListner != null) {
                                iWeatherListner.onWeatherDataResponseFailure(context.getResources().getString(R.string.server_error));
                            }
                        }

                        @Override
                        public void onNext(WeatherBean weatherBean) {
                            if (weatherBean != null && weatherBean.getDaily() != null
                                    && weatherBean.getDaily().getData() != null && weatherBean.getDaily().getData().size() > 0) {
                                dataList.addAll(weatherBean.getDaily().getData());
                            }
                        }
                    });
        } else {
            if (iWeatherListner != null) {
                iWeatherListner.onWeatherDataResponseFailure(context.getResources().getString(R.string.no_network));
            }
        }


    }
}

