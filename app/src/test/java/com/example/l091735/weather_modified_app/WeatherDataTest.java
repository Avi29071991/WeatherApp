package com.example.l091735.weather_modified_app;

import com.example.l091735.weather_modified_app.model.beans.WeatherBean;
import com.example.l091735.weather_modified_app.model.interfaces.WeatherAPI;
import com.example.l091735.weather_modified_app.presenter.WeatherImpl;
import com.example.l091735.weather_modified_app.utils.Codes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static org.mockito.Mockito.when;


/**
 * Created by L091735 on 31/10/2016.
 */

public class WeatherDataTest {

    private static final double LATITUDE = 19.082611;
    private static final double LONGITUDE = 72.6009923;

    private WeatherAPI weatherAPI;

    @Mock
    public WeatherImpl impl;


    private WeatherBean bean;

    @Before
    public void setUpBeforeTest() {

        /**
         * Required for testWeatherServiceMock()
         **/
        MockitoAnnotations.initMocks(this);


        /**
         * Required for testWeatherServiceCall()
         **/
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response;
            }
        });
        httpBuilder.connectTimeout(Codes.CONNECTION_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        httpBuilder.readTimeout(Codes.CONNECTION_READ_TIMEOUT, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.forecast.io/forecast/")
                .client(httpBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherAPI = retrofit.create(WeatherAPI.class);
    }

    @Test
    public void testWeatherServiceCall() {

        Call<WeatherBean> weatherCall = weatherAPI.fetchCurrentWeatherBean(Codes.API_KEY, LATITUDE, LONGITUDE);
        weatherCall.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, retrofit2.Response<WeatherBean> response) {
                if (response != null && response.isSuccessful()) {
                    WeatherBean bean = response.body();

                    Assert.assertTrue("Something went wrong while calling Weather API", (bean != null && bean.getDaily() != null
                            && bean.getDaily().getData() != null && bean.getDaily().getData().size() > 0));

                }
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                Assert.assertNotNull(t.getCause().toString(), null);
            }
        });


    }

    @Test
    public void testWeatherServiceMock() {
        when(impl.callWeatherAPI(LATITUDE, LONGITUDE)).thenReturn(Observable.just(bean));
    }

    @After
    public void checkAfterTest() {
        weatherAPI = null;
        bean = null;
        impl = null;
    }


}
