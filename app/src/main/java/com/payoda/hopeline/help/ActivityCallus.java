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
import com.payoda.hopeline.utils.AppUtils;
import com.payoda.hopeline.utils.GlobalConsts;

/**
 * Created by harpreet on 2/1/2016.
 */
public class ActivityCallus extends Activity implements View.OnClickListener {

    TextView footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callus);

        AppUtils.setScreenName(this, "Get Help");

        GlobalConsts.initBack(this);
        ((TextView)findViewById(R.id.head).findViewById(R.id.heading)).setText("Call Us");
        findViewById(R.id.call_icon).setOnClickListener(this);
        findViewById(R.id.call).setOnClickListener(this);
        findViewById(R.id.call_no).setOnClickListener(this);

        footer=(TextView)findViewById(R.id.bottomMark).findViewById(R.id.markText);

        if (GlobalConsts.footerLink_model.getCall_us_screen().length() < 80) {
            String temp = " ";
            for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getCall_us_screen().length()); i++)
                temp = temp + " ";
            footer.setText(GlobalConsts.footerLink_model.getCall_us_screen() + temp);

        } else
            footer.setText(GlobalConsts.footerLink_model.getCall_us_screen());


        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(GlobalConsts.footerLink_model.getCall_us_screen_link().equals("")
                        || GlobalConsts.footerLink_model.getCall_us_screen_link().equals("nolink"))) {

                    Uri uri = Uri.parse(GlobalConsts.footerLink_model.getCall_us_screen_link());
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
//        view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-phone-hours/");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.call_icon:

            case R.id.call:

            case R.id.call_no:

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+"+18003944673"));
                startActivity(callIntent);
                break;
        }
    }
}
