package com.fazrilabs.firstproject;

import android.util.Log;

/**
 * Created by fazri on 3/20/2016.
 */
public class Application extends com.activeandroid.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("Application", "onCreate");
    }
}
