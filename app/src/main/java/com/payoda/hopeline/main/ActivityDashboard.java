package com.payoda.hopeline.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.livechatinc.inappchat.ChatWindowActivity;
import com.payoda.hopeline.R;
import com.payoda.hopeline.feedback.ActivityFeedBack;
import com.payoda.hopeline.get_encouraged.InstagramFeed;
import com.payoda.hopeline.help.ActivityGetHelp;
import com.payoda.hopeline.listen.ActivityListen;
import com.payoda.hopeline.listen.CallTheShow;
import com.payoda.hopeline.prayer_show.PrayerFeedActivity;
import com.payoda.hopeline.utils.GlobalConsts;

import static com.payoda.hopeline.R.color.grey;


public class ActivityDashboard extends Activity implements View.OnClickListener {

    private Context context = this;
    TextView footer;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

//        int k = 1/0;
//        Log.e("crash","value "+k);

        //Init Firebase Analytics instance
        firebaseAnalytics  = FirebaseAnalytics.getInstance(this);
        firebaseAnalytics.setCurrentScreen(this, "Activity Dashboard", null /* class override */);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Dummy ID");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Testing");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        findViewById(R.id.get_help).setOnClickListener(this);
        findViewById(R.id.call_the_show).setOnClickListener(this);
        findViewById(R.id.get_encouraged).setOnClickListener(this);
        findViewById(R.id.listen).setOnClickListener(this);
        findViewById(R.id.chat_now).setOnClickListener(this);
        /* findViewById(R.id.follow).setOnClickListener(this);*/
        findViewById(R.id.feedback).setOnClickListener(this);

//        String summary = "<html><FONT color='#fdb728' FACE='courier'><marquee behavior='scroll' direction='left' scrollamount=10>"
//                + "Hello Droid" + "</marquee></FONT></html>";
//
//        webView.loadData(summary, "text/html", "utf-8");


//        WebView view1 = (WebView) findViewById(R.id.webView_footer);
//        view1.getSettings().setLoadsImagesAutomatically(true);
//        view1.getSettings().setJavaScriptEnabled(true);
//        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        view1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-marquee/");

        (findViewById(R.id.layer_marquee)).findViewById(R.id.markText).setSelected(true);
        footer = (TextView) findViewById(R.id.bottomMark).findViewById(R.id.markText);

        if (GlobalConsts.footerLink_model.getMobile_app_marquee().length() < 80) {
            String temp = " ";
            for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getMobile_app_marquee().length()); i++)
                temp = temp + " ";
            footer.setText(GlobalConsts.footerLink_model.getMobile_app_marquee() + temp);

        } else
            footer.setText(GlobalConsts.footerLink_model.getMobile_app_marquee());

        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(GlobalConsts.footerLink_model.getMobile_app_marquee_link().equals("")
                        || GlobalConsts.footerLink_model.getMobile_app_marquee_link().equals("nolink"))) {

                    Uri uri = Uri.parse(GlobalConsts.footerLink_model.getMobile_app_marquee_link());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });


    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(final View v) {
        v.setBackgroundColor((getResources().getColor(grey)));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;

                switch (v.getId()) {

                    case R.id.chat_now:

                        intent = new Intent(context, ChatWindowActivity.class);
//                        intent.putExtra(ChatWindowActivity.KEY_GROUP_ID, "0");
                        intent.putExtra(ChatWindowActivity.KEY_LICENCE_NUMBER, "6982601");
//                        intent.putExtra(ChatWindowActivity.KEY_VISITOR_NAME, "Client");
//                        intent.putExtra(ChatWindowActivity.KEY_VISITOR_EMAIL, "client@example.com");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.text_color));

                        break;


                    case R.id.get_help:

                        startActivity(new Intent(context, ActivityGetHelp.class));
                        v.setBackgroundColor(getResources().getColor(R.color.text_color));

                        break;

                    case R.id.get_encouraged:
//                        intent = new Intent(context, InstagramFeed.class);
//                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"get-instagram-feeds-mobile-app/");
//                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"get-instagram-feeds-mobile-app/");
//                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Instagram Feed");
                        intent = new Intent(context, PrayerFeedActivity.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL + "theprayershow/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE, getResources().getString(R.string.theprayershow));
                        startActivity(intent);
//                        intent = new Intent(context, InstagramFeed.class);
//                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.get_encourged_color));
                        break;


                    case R.id.listen:
                        startActivity(new Intent(context, ActivityListen.class));
                        v.setBackgroundColor(getResources().getColor(R.color.listen_color));
                        break;

                  /*  case R.id.follow:
                        intent = new Intent(context, InstagramFeed.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"get-twitter-feeds-mobile-app/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Twitter Feed");
                        startActivity(intent);
//                        intent = new Intent(context, InstagramFeed.class);
//                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.twitter_blue));
                        break;
*/
                    case R.id.feedback:
                        startActivity(new Intent(context, ActivityFeedBack.class));
                        v.setBackgroundColor(Color.TRANSPARENT);
                        break;
                    case R.id.call_the_show:
                        Intent intent_call = new Intent(ActivityDashboard.this, CallTheShow.class);
                        startActivity(intent_call);
                        v.setBackgroundColor(getResources().getColor(R.color.phone_color));

                        break;

                    default:
                        break;
                }

            }
        }, 150);
    }
}
