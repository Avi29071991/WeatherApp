package com.westpac.l091735.weatherforecastapp;

import android.support.test.filters.SmallTest;
import android.test.InstrumentationTestCase;

/*import com.westpac.l091735.weatherforecastapp.model.beans.WeatherBean;
import com.westpac.l091735.weatherforecastapp.model.interfaces.WeatherAPI;*/
import com.westpac.l091735.weatherforecastapp.utils.Codes;

import org.junit.Assert;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;


/**
 * Created by L091735 on 1/11/2016.
 */

public class WeatherMainActivityTest extends InstrumentationTestCase {


    private MockRetrofit mockRetrofit;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Codes.BASE_WEATHER_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @SmallTest
    public void testWeatherIsShown() throws Exception {
        /*BehaviorDelegate<WeatherAPI> delegate = mockRetrofit.create(WeatherAPI.class);
        WeatherAPI mockQodService = new MockWeatherTestService(delegate);

        Call<WeatherBean> weatherCall = mockQodService.fetchStubbedWeatherBean();
        Response<WeatherBean> weatherResponse = weatherCall.execute();
        Assert.assertTrue(weatherResponse.isSuccessful());
        Assert.assertNotNull(weatherResponse.body().getDaily().getData());
        Assert.assertEquals("The size of the daily data array is 8.", 8, weatherResponse.body().getDaily().getData().size());*/
    }


}
