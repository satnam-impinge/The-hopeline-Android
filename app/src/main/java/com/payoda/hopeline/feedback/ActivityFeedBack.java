package com.payoda.hopeline.feedback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.payoda.hopeline.R;
import com.payoda.hopeline.main.ActivityWebView;
import com.payoda.hopeline.utils.GlobalConsts;

import static com.payoda.hopeline.R.color.grey;

public class ActivityFeedBack extends Activity implements View.OnClickListener {

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feedback);
        GlobalConsts.initBack(this);


        ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText("Feedback");


        if (GlobalConsts.footerLink_model.getFeedback_screen().length() < 80) {
            String temp = " ";
            for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getFeedback_screen().length()); i++)
                temp = temp + " ";
            ((TextView) findViewById(R.id.layer_marquee).findViewById(R.id.markText)).setText(GlobalConsts.footerLink_model.getFeedback_screen() + temp);

        } else
            ((TextView) findViewById(R.id.layer_marquee).findViewById(R.id.markText)).setText(GlobalConsts.footerLink_model.getFeedback_screen());

        ((TextView) findViewById(R.id.layer_marquee).findViewById(R.id.markText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(GlobalConsts.footerLink_model.getFeedback_screen_link().equals("")
                        || GlobalConsts.footerLink_model.getFeedback_screen_link().equals("nolink"))) {

                    Uri uri = Uri.parse(GlobalConsts.footerLink_model.getFeedback_screen_link());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });


//        WebView view1 = (WebView) findViewById(R.id.webView_footer);
//        view1.getSettings().setLoadsImagesAutomatically(true);
//        view1.getSettings().setJavaScriptEnabled(true);
//        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        view1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-marquee/");

        findViewById(R.id.problm_with_app).setOnClickListener(this);
        findViewById(R.id.tell_us).setOnClickListener(this);
        /*findViewById(R.id.send_voice_msg).setOnClickListener(this);*/

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(final View v) {

        v.setBackgroundColor((getResources().getColor(grey)));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                switch (v.getId()) {
                    case R.id.problm_with_app:
                        startActivity(new Intent(context, ActivityProblemWithApp.class));
                        v.setBackgroundColor(getResources().getColor(R.color.white));
                        break;

                    case R.id.tell_us:
                        Intent intent = new Intent(context, ActivityWebView.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, "https://www.thehopeline.com/mobile-app-term-condition/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Terms of Use");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.chat_green));

                      /*  Intent intent = new Intent(context,ActivityWebView.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"mobile-app-term-condition/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE,"Feedback");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.chat_green));
                        break;*/

                    /*case R.id.send_voice_msg:
                        startActivity(new Intent(context, Activity_VoiceMsg_Form.class));
                        v.setBackgroundColor(getResources().getColor(R.color.white));
                        break;*/

                    default:
                        break;
                }

            }
        }, 150);
    }
}
