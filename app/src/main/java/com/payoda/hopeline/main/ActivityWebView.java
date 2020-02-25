package com.payoda.hopeline.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.GlobalConsts;

public class ActivityWebView extends Activity {

    private Context context = this;
    private ProgressDialog dialog;
    TextView footer;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        GlobalConsts.initBack(this);

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

        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading ...");

        Intent intent = this.getIntent();

        String url = "";
        if (intent.hasExtra(GlobalConsts.EXTRA_URL))
            url = intent.getStringExtra(GlobalConsts.EXTRA_URL);


        if (intent.hasExtra(GlobalConsts.EXTRA_TITLE))
            ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText(intent.getStringExtra(GlobalConsts.EXTRA_TITLE));

        if (intent.hasExtra(GlobalConsts.EXTRA_TITLE))
            ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText(intent.getStringExtra(GlobalConsts.EXTRA_TITLE));

        WebView view = (WebView) findViewById(R.id.webView);
        view.setWebViewClient(new MyBrowser());
        view.getSettings().setLoadsImagesAutomatically(true);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        view.loadUrl(url);

//        view.loadUrl("http://flexischools.com.au/");

        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT > 20)
            CookieManager.getInstance().setAcceptThirdPartyCookies(view, true);
        if (intent.getStringExtra(GlobalConsts.EXTRA_TITLE).equals("Chat with Us")) {
            findViewById(R.id.bottomMark).findViewById(R.id.markText).setVisibility(View.GONE);
        }

        view.requestFocus(View.FOCUS_DOWN);

//        WebView view1 = (WebView) findViewById(R.id.webView_footer);
////        view1.setWebViewClient(new MyBrowser());
//        view1.getSettings().setLoadsImagesAutomatically(true);
//        view1.getSettings().setJavaScriptEnabled(true);
//        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        view1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        view1.loadUrl(GlobalConsts.bete_URL+"mobile-app-marquee/");
////        view1.setOnTouchListener (new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                Toast.makeText(ActivityWebView.this, " web click", Toast.LENGTH_LONG).show();
////                return false;
////            }
////        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (dialog != null && !dialog.isShowing())
                if (!ActivityWebView.this.isDestroyed()){
                    dialog.show();
                }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (dialog.isShowing())
                if (!ActivityWebView.this.isDestroyed()) {
                    dialog.dismiss();
                }
        }
    }
}
