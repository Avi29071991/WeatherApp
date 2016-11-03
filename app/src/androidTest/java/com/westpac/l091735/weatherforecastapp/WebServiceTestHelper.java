package com.westpac.l091735.weatherforecastapp;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by L091735 on 1/11/2016.
 */

class WebServiceTestHelper {
    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    static String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);

        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }
}
