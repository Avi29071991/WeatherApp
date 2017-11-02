package com.westpac.l091735.weatherforecastapp;

//import com.westpac.l091735.weatherforecastapp.model.beans.WeatherBean;
//import com.westpac.l091735.weatherforecastapp.model.interfaces.WeatherAPI;
import com.westpac.l091735.weatherforecastapp.utils.Codes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.observers.TestSubscriber;


/**
 * Created by L091735 on 31/10/2016.
 */

public class WeatherDataTest {

    private static final double LATITUDE = 19.082611;
    private static final double LONGITUDE = 72.6009923;


//    private WeatherImpl impl;
//
//    @Before
//    public void setUpBeforeTest() {
//
//        NetworkingModules netModules = new NetworkingModules(Codes.BASE_WEATHER_URL);
//        OkHttpClient client = netModules.getOkHttpClientInstance();
//        Retrofit retrofit1 = netModules.getRetrofitInstance(client);
//        WeatherAPI weatherAPI = retrofit1.create(WeatherAPI.class);
//
//        impl = new WeatherImpl();
//        impl.setApiService(weatherAPI);
//
//    }
//
//    @Test
//    public void testWithBlocker() {
//        WeatherBean bean = impl.callWeatherAPI(LATITUDE, LONGITUDE).toBlocking().first();
//        Assert.assertNotNull(bean);
//    }
//
//    @Test
//    public void testWithSubscriber() {
//        Observable<WeatherBean> observableObj = impl.callWeatherAPI(LATITUDE, LONGITUDE);
//        TestSubscriber<WeatherBean> testSubscriber = new TestSubscriber<>();
//        observableObj.subscribe(testSubscriber);
//
//        testSubscriber.assertNoErrors();
//        List<WeatherBean> weatherList = testSubscriber.getOnNextEvents();
//        Assert.assertEquals(weatherList.size(), 1);
//    }
//
//
//    @After
//    public void clearUpAfterTest() {
//        impl = null;
//    }


}
