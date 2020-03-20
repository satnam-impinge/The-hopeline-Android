package com.payoda.hopeline.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.FooterLink_Model;
import com.payoda.hopeline.utils.GlobalConsts;
import com.payoda.hopeline.webapi.WebApi;

import org.json.JSONObject;


public class ActivitySplash extends Activity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        new Async_Web().execute();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(context, ActivityDashboard.class));
//                finish();
//            }
//        }, 1500);
    }

    class Async_Web extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            Log.e("doInBackground", "doInBackground");
            WebApi webApi = WebApi.getInstance();
            String s = webApi.webcall(ActivitySplash.this, "https://www.thehopeline.com/mobile-app-android-api/");
            Log.e("String", " s = "+s);
            return s;
        }


        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            try {
                JSONObject obj = new JSONObject(o);
                FooterLink_Model footerLink_model = new FooterLink_Model();

                footerLink_model.setDawson_voice(obj.getJSONObject("dawson_voice_msg").getString("content"));

                if (obj.getJSONObject("dawson_voice_msg").has("link"))
                    footerLink_model.setDawson_voice_link(obj.getJSONObject("dawson_voice_msg").getString("link"));
                else
                    footerLink_model.setDawson_voice_link("nolink");


                footerLink_model.setFeedback_screen(obj.getJSONObject("feedback_screen").getString("content"));
                if (obj.getJSONObject("feedback_screen").has("link"))
                    footerLink_model.setFeedback_screen_link(obj.getJSONObject("feedback_screen").getString("link"));
                else
                    footerLink_model.setFeedback_screen_link("nolink");

                footerLink_model.setApp_error_screen(obj.getJSONObject("app_error_screen").getString("content"));
                if (obj.getJSONObject("app_error_screen").has("link"))
                    footerLink_model.setApp_error_screen_link(obj.getJSONObject("app_error_screen").getString("link"));
                else
                    footerLink_model.setApp_error_screen_link("nolink");

                footerLink_model.setListen_screen(obj.getJSONObject("listen_screen").getString("content"));
                if (obj.getJSONObject("listen_screen").has("link"))
                    footerLink_model.setListen_screen_link(obj.getJSONObject("listen_screen").getString("link"));
                else
                    footerLink_model.setListen_screen_link("nolink");

                footerLink_model.setGet_encouraged_screen(obj.getJSONObject("get_encouraged_screen").getString("content"));
                if (obj.getJSONObject("get_encouraged_screen").has("link"))
                    footerLink_model.setGet_encouraged_screen_link(obj.getJSONObject("get_encouraged_screen").getString("link"));
                else
                    footerLink_model.setGet_encouraged_screen_link("nolink");

                footerLink_model.setChat_online_screen(obj.getJSONObject("chat_online_screen").getString("content"));
                if (obj.getJSONObject("chat_online_screen").has("link"))
                    footerLink_model.setChat_online_screen_link(obj.getJSONObject("chat_online_screen").getString("link"));
                else
                    footerLink_model.setChat_online_screen_link("nolink");

                footerLink_model.setCall_us_screen(obj.getJSONObject("call_us_screen").getString("content"));
                if (obj.getJSONObject("call_us_screen").has("link"))
                    footerLink_model.setCall_us_screen_link(obj.getJSONObject("call_us_screen").getString("link"));
                else
                    footerLink_model.setCall_us_screen_link("nolink");

                footerLink_model.setMobile_app_marquee(obj.getJSONObject("mobile_app_marquee").getString("content"));
                if (obj.getJSONObject("mobile_app_marquee").has("link"))
                    footerLink_model.setMobile_app_marquee_link(obj.getJSONObject("mobile_app_marquee").getString("link"));
                else
                    footerLink_model.setMobile_app_marquee_link("nolink");

                footerLink_model.setFollow_screen(obj.getJSONObject("follow_screen").getString("content"));
                if (obj.getJSONObject("follow_screen").has("link"))
                    footerLink_model.setFollow_screen_link(obj.getJSONObject("follow_screen").getString("link"));
                else
                    footerLink_model.setFollow_screen_link("nolink");





                footerLink_model.setEmail_mentor(obj.getJSONObject("email_mentor").getString("content"));
                if (obj.getJSONObject("email_mentor").has("link"))
                    footerLink_model.setEmail_mentor_link(obj.getJSONObject("email_mentor").getString("link"));
                else
                    footerLink_model.setEmail_mentor_link("nolink");

                GlobalConsts.footerLink_model = footerLink_model;
                startActivity(new Intent(context, ActivityDashboard.class));
                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}
