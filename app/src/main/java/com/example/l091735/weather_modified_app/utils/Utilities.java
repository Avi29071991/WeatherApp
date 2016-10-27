package com.example.l091735.weather_modified_app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;
import java.util.Map;


public class Utilities {


    /***
     * To check if the user is online
     ***/
    public static boolean isOnline(Context context) {
        try {
            if (context != null) {

                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getNetworkInfo(0);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                } else {
                    netInfo = cm.getNetworkInfo(1);
                    if (netInfo != null
                            && netInfo.getState() == NetworkInfo.State.CONNECTED)
                        return true;
                    else
                        return false;
                }
            } else
                return false;
        } catch (Exception exp) {
            return false;
        }

    }

    /***
     * To check if context is not null
     ***/
    public static boolean isAlive(Context context) {
        try {
            if (context != null && !((Activity) context).isFinishing()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /***
     * To check if string value is not null or empty
     ***/

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Object obj) {
        boolean result = true;
        if (obj != null) {
            if (obj instanceof String) {

                if (obj.toString().trim().length() != 0
                        && !obj.toString().trim().equalsIgnoreCase("null")
                        && !obj.toString().trim().equalsIgnoreCase(""))
                    result = false;
            } else if (obj instanceof List) {
                if (((List) obj).size() > 0)
                    result = false;
            } else if (obj instanceof Map) {
                if (((Map) obj).size() > 0)
                    result = false;
            }
        }

        return !result;

    }

    /***
     * To check if the application has specified permission for Android Marshmallow
     ***/
    public static boolean checkPermission(Context context, String requiredPermission) {
        PackageManager pm = context.getPackageManager();
        int hasRequiredPermission = pm.checkPermission(
                requiredPermission,
                context.getPackageName());
        if (hasRequiredPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    /***
     * To convert temperature from Farhenite to Celius
     ***/
    public static float convertFarheniteToCelcius(double farheit) {
        // TODO Auto-generated method stub
        double celcius;
        float x;
        celcius = ((farheit - 32) * 5) / 9;
        x = (float) Math.round(celcius);
        return x;
    }
}
