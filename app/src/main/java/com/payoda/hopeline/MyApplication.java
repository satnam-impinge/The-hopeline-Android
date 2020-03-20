package com.payoda.hopeline;

import android.app.Activity;
import android.content.Context;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public class MyApplication extends MultiDexApplication {
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


}