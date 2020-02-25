package com.payoda.hopeline.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.payoda.hopeline.R;

import java.net.URI;


public class GlobalConsts {

//    sdjfl;ash;dfhklasd
//    public static final String DMLive = "http://icy3.abacast.com/shepherdscall-hopenowmp3-48";
    public static final String DMLive = "https://live.wostreaming.net/direct/shepherdscall-talkmp3-ibc1?soure=DMLive";
    public static final String HotLine = "http://icy3.abacast.com/shepherdscall-hopelinereplayaac-48";
    public static final String PLAYING = "PLAYING";
    public static final String PAUSE = "PAUSED";
    public static String Player1_tag = PAUSE;
    public static String Player2_tag = PAUSE;
    public static boolean is_mP1_ready = false;
    public static boolean is_mP2_ready = false;
    public static boolean is_mP1_perparing = false;
    public static boolean is_mP2_perparing = false;
    public static boolean should_prepare = true;


    public static String URL = "https://www.thehopeline.com/";
    public static String STRINGURL = "https://beta.www.thehopeline.com/";
//    public static String URL = "http://beta.www.thehopeline.com/";


    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";

    // INSTAGRAM VALUES

    public static final String CLIENT_ID = "a27797f0196d4a74829664c8d006823a";
    public static final String CLIENT_SECRET = "c4ea4ce9ad67415e9d24e29a7b8692d8";
    public static final String WEBSITE_URL = "http://hopeline.com/";
    public static final String REDIRECT_URL = "http://hopeline.com/";
    public static final String EMAIL_USED = "harmansohi91@yahoo.com";

    public static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    //Used for Authentication.
    public static final String TOKENURL = "https://api.instagram.com/oauth/access_token";
    //Used for getting token and User details.
    public static final String APIURL = "https://api.instagram.com/v1";
    //Used to specify the API version which we are going to use.
    public static String CALLBACKURL = REDIRECT_URL;


    public static final String authURLString = AUTHURL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + CALLBACKURL + "&response_type=code&display=touch&scope=likes+comments+relationships";
    public static final String tokenURLString = TOKENURL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";

    public static void initBack(final Activity activity) {
        TextView mrkext;
        activity.findViewById(R.id.head).findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        activity.findViewById(R.id.head).findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        mrkext = (TextView) (activity.findViewById(R.id.bottomMark)).findViewById(R.id.markText);
        mrkext.setSelected(true);

    }

    public static FooterLink_Model  footerLink_model=new FooterLink_Model();

//
//    // Footer text
//    public static String feedback_screen = "HopeCoaches are available to CHAT 24/7";
//    public static String app_error_screen = "TheHopeLine is open 24/7 for CHAT";
//    public static String listen_screen = "Dawson McAllister Radio #sharehope";
//    public static String follow_screen = "Follow @TheHopeLine on twitter, youtube, instagram, and facebook. https://twitter.com/thehopeline";
//    public static String get_encouraged_screen = "#sharehope real stories. real people. www.youtube.com/thehopeline check it out today.";
//    public static String chat_online_screen = "CHAT with a HopeCoach 24/7";
//    public static String call_us_screen = "Telephone hours - Sat 8pm to 11pm, Sun 8pm to Monday 2am. CHAT is open 24/7.";
//    public static String mobile_app_marquee = "24/7 HopeCoaches are available to CHAT with you";
}
