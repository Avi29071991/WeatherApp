package com.avinash.weatherapp.forecastapp;

import com.avinash.weatherapp.forecastapp.utils.Utilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by L091735 on 27/10/2016.
 */

public class ConverterTest {

    @Test
    public void testConvertFarheniteToCelciusSuccess() {

        float actual = Utilities.convertFarheniteToCelcius(50);


        float expected = 10;

        assertEquals("Farhenite to Celcius conversion is successful ", expected,
                actual, 0.001);
    }

    @Test
    public void testConvertFarheniteToCelciusFailed() {
        float actual = Utilities.convertFarheniteToCelcius(50);
        float expected = 20;
        assertEquals("Farhenite to Celcius conversion is failed ", expected,
                actual, 0.001);
    }
}
