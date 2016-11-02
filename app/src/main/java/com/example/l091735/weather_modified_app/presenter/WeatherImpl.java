package com.example.l091735.weather_modified_app.presenter;

import android.content.Context;
import android.util.Log;


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

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class WeatherImpl {

    private Context context;

    @Inject
    WeatherAPI apiService;

    /**
     * Sets the object of WeatherAPI which is used for Unit testing using JUnit.
     *
     * @param apiService the object of WeatherAPI service which needs to be set.
     **/
    public void setApiService(WeatherAPI apiService) {
        this.apiService = apiService;
    }


    /**
     * Empty constructor used for initializing this class for Unit testing using JUnit.
     **/
    public WeatherImpl() {
    }


    /**
     * Constructor of WeatherImpl class which sets the context used for further webservice call operations
     *
     * @param context context of the activity which is used for injecting dependency classes.
     **/
    public WeatherImpl(Context context) {
        this.context = context;
        ((MyWeatherApplication) context).getAppComponent().injectPojo(this);
    }

    /**
     * Returns the Observable object of Weather bean which can be subscribed for fetching weather data.
     *
     * @param latitude  double value which represents the latitude of the updated location.
     * @param longitude double value which represents the longitude of the updated location.
     * @return Observable<Weather> which is used for further subscription.
     **/
    public Observable<WeatherBean> callWeatherAPI(double latitude, double longitude) {

        return apiService.fetchCurrentWeather(Codes.API_KEY, latitude, longitude).flatMap(new Func1<WeatherBean, Observable<? extends WeatherBean>>() {
            @Override
            public Observable<? extends WeatherBean> call(WeatherBean weatherBean) {
                return weatherBean.filterWebService();
            }
        });
    }


    /**
     * Processes the observable object to fetch weather data and calls
     * onNext if data is fetched successfully OR onError if there occurs a problem while fetching data.
     *
     * @param latitude        double value which represents the latitude of the updated location.
     * @param longitude       double value which represents the longitude of the updated location.
     * @param iWeatherListner interface which will be called for displaying data or error depending on the condition provided.
     **/

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
                                if (dataList.size() > 0) {
                                    iWeatherListner.onWeatherDataResponseSuccessful(dataList);
                                } else {
                                    iWeatherListner.onWeatherDataResponseFailure(context.getResources().getString(R.string.no_data));
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("WeatherImpl", "Inside OnError Method of Subscription", e);

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

