package com.example.l091735.weather_modified_app.presenter.network;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by L091735 on 26/10/2016.
 */

public class RetroFitInitializer {

    private Context context;

    public RetroFitInitializer(Context ctx) {
        this.context = ctx;
    }

    /****
     * Initializing Retrofit Component for calling the webservices
     ****/

    public Retrofit initializeRetrofit(String baseUrl) {

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response;
            }
        });
        httpBuilder.connectTimeout(20000, TimeUnit.MILLISECONDS);
        httpBuilder.readTimeout(20000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
