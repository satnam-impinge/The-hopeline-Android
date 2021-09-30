package com.payoda.hopeline;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.UserAttributes;


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
        new GetPublicIP().execute();
        Intercom.initialize(this, "android_sdk-597c68cec8b16403fc727d3f31acaba99f8c7abe", "pnsu3l0n");
        Intercom.client().registerUnidentifiedUser();


    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public class GetPublicIP extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String publicIP = "";
            try {
                java.util.Scanner s = new java.util.Scanner(
                        new java.net.URL(
                                "https://api.ipify.org")
                                .openStream(), "UTF-8")
                        .useDelimiter("\\A");
                publicIP = s.next();
                System.out.println("My current IP address is " + publicIP);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }

            return publicIP;
        }

        @Override
        protected void onPostExecute(String publicIp) {
            super.onPostExecute(publicIp);
            String IP_ADDRESS = publicIp;
            Log.e("ips_address", IP_ADDRESS);

            UserAttributes userAttributes = new UserAttributes.Builder()
                    .withCustomAttribute("chatPartner", "TheHopeLine Android App")
                    .withCustomAttribute("ipaddress", IP_ADDRESS)
                    .build();
            Intercom.client().updateUser(userAttributes);

        }


    }
}