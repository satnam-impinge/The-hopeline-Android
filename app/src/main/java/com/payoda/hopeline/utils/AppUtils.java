package com.payoda.hopeline.utils;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;


public class AppUtils {

    public static void setScreenName(Activity activity, String screenName){
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);
        firebaseAnalytics.setCurrentScreen(activity, screenName, null);
    }
}
