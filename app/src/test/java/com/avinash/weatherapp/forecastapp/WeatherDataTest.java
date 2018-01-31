package com.avinash.weatherapp.forecastapp;

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
