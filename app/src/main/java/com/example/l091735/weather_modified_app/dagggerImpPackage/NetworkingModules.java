package com.example.l091735.weather_modified_app.dagggerImpPackage;

import com.example.l091735.weather_modified_app.model.interfaces.WeatherAPI;
import com.example.l091735.weather_modified_app.utils.Codes;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by L091735 on 28/10/2016.
 */

@Module
public class NetworkingModules {

    private String weatherBaseUrl;

    public NetworkingModules(String weatherBaseUrl) {
        this.weatherBaseUrl = weatherBaseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClientInstance() {
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

        return httpBuilder.build();
    }

    @Provides
    @Singleton
    public Retrofit getRetrofitInstance(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(weatherBaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    WeatherAPI getWeatherAPIInstance(Retrofit retrofit) {
        return retrofit.create(WeatherAPI.class);
    }
}
