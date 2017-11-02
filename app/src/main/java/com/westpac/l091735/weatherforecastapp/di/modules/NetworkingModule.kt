package com.westpac.l091735.weatherforecastapp.di.modules

import com.westpac.l091735.network.service.ApiService
import com.westpac.l091735.weatherforecastapp.modelconverter.ModelConverter
import com.westpac.l091735.weatherforecastapp.utils.Codes
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun providesOkHttpClientInstance(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder.addInterceptor { chain ->
            val response = chain.proceed(chain.request())
            response
        }
        httpBuilder.connectTimeout(Codes.CONNECTION_READ_TIMEOUT, TimeUnit.MILLISECONDS)
        httpBuilder.readTimeout(Codes.CONNECTION_READ_TIMEOUT, TimeUnit.MILLISECONDS)

        return httpBuilder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
                .baseUrl(Codes.BASE_WEATHER_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit
    }

    @Provides
    @Singleton
    fun providesApiInstance(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesModelConverter(): ModelConverter {
        return ModelConverter()
    }
}