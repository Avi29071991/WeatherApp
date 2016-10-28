package com.example.l091735.weather_modified_app.dagggerImpPackage;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by L091735 on 28/10/2016.
 */

@Module
public class AndroidModule {

    Application context;

    public AndroidModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context getContextInstance() {
        return context;
    }

    @Provides
    @Singleton
    public LayoutInflater getLayoutInflater() {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

}
