package com.payoda.hopeline.help;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.payoda.hopeline.MyApplication;
import com.payoda.hopeline.R;
import com.payoda.hopeline.main.ActivityWebView;
import com.payoda.hopeline.utils.AppUtils;
import com.payoda.hopeline.utils.GlobalConsts;

/**
 * Created by harpreet on 2/1/2016.
 */
public class ActivityEmailMentor extends Activity implements View.OnClickListener {

    TextView footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailmentor);

        AppUtils.setScreenName(this, "Get Help");

        GlobalConsts.initBack(this);
        ((TextView)findViewById(R.id.head).findViewById(R.id.heading)).setText("Mentor Request");
        findViewById(R.id.request).setOnClickListener(this);

        footer=(TextView)findViewById(R.id.bottomMark).findViewById(R.id.markText);

        if (GlobalConsts.footerLink_model.getEmail_mentor().length() < 80) {
            String temp = " ";
            for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getEmail_mentor().length()); i++)
                temp = temp + " ";
            footer.setText(GlobalConsts.footerLink_model.getEmail_mentor() + temp);

        } else
            footer.setText(GlobalConsts.footerLink_model.getEmail_mentor());

        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(GlobalConsts.footerLink_model.getEmail_mentor().equals("")
                        || GlobalConsts.footerLink_model.getEmail_mentor().equals("nolink"))) {

                    Uri uri = Uri.parse(GlobalConsts.footerLink_model.getEmail_mentor());
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
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.request:
                intent = new Intent(this, ActivityWebView.class);
                intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"email-mentor-signup/");
                intent.putExtra(GlobalConsts.EXTRA_TITLE, "Mentor Request");
                startActivity(intent);
                v.setBackgroundColor(getResources().getColor(R.color.green));
                break;
        }
    }
}
