package com.payoda.hopeline.prayer_show;

/**
 * Created by harpreet on 2/2/2016.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ShareCompat;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.payoda.hopeline.MyApplication;
import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.AppUtils;
import com.payoda.hopeline.utils.GlobalConsts;

import java.net.URLEncoder;
import java.util.List;


public class PrayerFeedActivity extends Activity implements View.OnClickListener {

    private Context context = this;
    private ProgressDialog dialog;
    LinearLayout sharing_lay, twitter_link;
    ImageView twitter_icon, fb_icon, g_plus_icon;
    TextView footer;
    Uri uri = null;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_feed);

        AppUtils.setScreenName(this, "Prayer Show");

        GlobalConsts.initBack(this);

        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading ...");

        Intent intent = this.getIntent();

        String url = "";
        if (intent.hasExtra(GlobalConsts.EXTRA_URL))
            url = intent.getStringExtra(GlobalConsts.EXTRA_URL);

        if (intent.hasExtra(GlobalConsts.EXTRA_TITLE))
            ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText(intent.getStringExtra(GlobalConsts.EXTRA_TITLE));

        WebView view = (WebView) findViewById(R.id.webView);
        view.setWebViewClient(new MyBrowser());
        view.getSettings().setLoadsImagesAutomatically(true);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        view.loadUrl(url);
        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT > 20)
            CookieManager.getInstance().setAcceptThirdPartyCookies(view, true);

        sharing_lay = (LinearLayout) findViewById(R.id.sharing_lay);

        footer = (TextView) findViewById(R.id.bottomMark).findViewById(R.id.markText);
        footer.setOnClickListener(this);
//        findViewById(R.id.bottomMark).setOnClickListener(this);

        TextView cancel = (TextView) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        twitter_icon = (ImageView) findViewById(R.id.twitter_icon);
        twitter_icon.setOnClickListener(this);
        fb_icon = (ImageView) findViewById(R.id.fb_icon);
        fb_icon.setOnClickListener(this);
        g_plus_icon = (ImageView) findViewById(R.id.g_plus_icon);
        g_plus_icon.setOnClickListener(this);


        twitter_link = (LinearLayout) findViewById(R.id.twitter_link);
        twitter_link.setOnClickListener(this);
//        WebView view1 = (WebView) findViewById(R.id.webView_footer);
//        view1.getSettings().setLoadsImagesAutomatically(true);
//        view1.getSettings().setJavaScriptEnabled(true);
//        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        view1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if (intent.getStringExtra(GlobalConsts.EXTRA_TITLE).equals("Twitter Feed")) {
            twitter_link.setVisibility(View.VISIBLE);

            if (GlobalConsts.footerLink_model.getFollow_screen().length() < 80) {
                String temp = " ";
                for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getFollow_screen().length()); i++)
                    temp = temp + " ";
                footer.setText(GlobalConsts.footerLink_model.getFollow_screen() + temp);

            } else
                footer.setText(GlobalConsts.footerLink_model.getFollow_screen());

//            footer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (!(GlobalConsts.footerLink_model.getFollow_screen_link().equals("")
//                            || GlobalConsts.footerLink_model.getFollow_screen_link().equals("nolink"))) {
//
//                        uri = Uri.parse(GlobalConsts.footerLink_model.getFollow_screen_link());
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
//                    }
//                }
//            });


//            view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-social-media-ios/");
        } else {
            twitter_link.setVisibility(View.GONE);

//            view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-social-media/");

            if (GlobalConsts.footerLink_model.getGet_encouraged_screen().length() < 80) {
                String temp = " ";
                for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getGet_encouraged_screen().length()); i++)
                    temp = temp + " ";
                footer.setText(GlobalConsts.footerLink_model.getGet_encouraged_screen() + temp);

            } else
                footer.setText(GlobalConsts.footerLink_model.getGet_encouraged_screen());


//            footer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (!(GlobalConsts.footerLink_model.getGet_encouraged_screen_link().equals("")
//                            || GlobalConsts.footerLink_model.getGet_encouraged_screen_link().equals("nolink"))) {
//
//                        uri = Uri.parse(GlobalConsts.footerLink_model.getGet_encouraged_screen_link());
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
//                    }
//                }
//            });


        }


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        List<ResolveInfo> matches = null;
        switch (v.getId()) {

            case R.id.markText:
                footer.setVisibility(View.GONE);
                sharing_lay.setVisibility(View.VISIBLE);
                break;

            case R.id.insta_link:
                uri = Uri.parse("https://www.instagram.com/thehopeline/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.twitter_link:
                uri = Uri.parse("https://twitter.com/thehopeline");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.cancel:
                footer.setVisibility(View.VISIBLE);
                sharing_lay.setVisibility(View.GONE);
                break;

            case R.id.twitter_icon:
                String tweettxt = "Share This!\n" +
                        " #sharehope real stories. real people. www.youtube.com/thehopeline check it out today.";
                String tweetUrl =
                        String.format("https://twitter.com/intent/tweet?text=%s&url=%s",
                                urlEncode(tweettxt), urlEncode("www.youtube.com/thehopeline/"));
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
                matches = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                        intent.setPackage(info.activityInfo.packageName);
                    }
                }
                startActivity(intent);
                break;

            case R.id.fb_icon:
                String urlToShare = "http://thehope.dm/1y83oeV";
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                boolean facebookAppFound = false;
                matches = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                        intent.setPackage(info.activityInfo.packageName);
                        facebookAppFound = true;
                        break;
                    }
                }
                if (!facebookAppFound) {
                    String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                }
                startActivity(intent);
                break;

            case R.id.g_plus_icon:
                Intent shareIntent = ShareCompat.IntentBuilder.from(this).setText("http://thehope.dm/1y83oeV").getIntent();
                shareIntent.setType("text/plain");
                shareIntent.setPackage("com.google.android.apps.plus");
                startActivity(shareIntent);
                break;
        }
    }

    public String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            return null;
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
                if (!PrayerFeedActivity.this.isDestroyed()){
                    dialog.show();
                }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (dialog.isShowing()) {
                if (!PrayerFeedActivity.this.isDestroyed()) {
                    dialog.dismiss();
                }
            }
        }
    }
}

//public class InstagramFeed extends Activity {
//
//    private Context context = this;
//    private ProgressDialog dialog;
//    String request_token = null, urlString = null;
//    String accessTokenString, id, username;
//
//    @SuppressLint("SetJavaScriptEnabled")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_insta_web);
//
//        GlobalConsts.initBack(this);
//
//        dialog = new ProgressDialog(context);
//        dialog.setMessage("Loading ...");
//
//
//        ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText("Instagram Feed");
//
//        WebView webView = (WebView) findViewById(R.id.webView);
//        webView.setVerticalScrollBarEnabled(false);
//        webView.setHorizontalScrollBarEnabled(false);
//        webView.setWebViewClient(new AuthWebViewClient());
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(GlobalConsts.authURLString);
//
//
//    }
//
//    private class AuthWebViewClient extends WebViewClient {
//
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            if (dialog != null && !dialog.isShowing())
//                dialog.show();
//
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            if (dialog.isShowing())
//                dialog.dismiss();
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            if (url.startsWith(GlobalConsts.CALLBACKURL)) {
//                System.out.println(url);
//                String parts[] = url.split("=");
//                request_token = parts[1];  //This is your request token.
//                Log.e("request_token", "request_token=" + request_token);
//                new GetAccessToken().execute();
//                return true;
//            }
//            return false;
//        }
//
//    }
//
//    private class GetAccessToken extends AsyncTask {
//        @Override
//        protected Object doInBackground(Object[] params) {
//            try {
//                URL url = new URL(GlobalConsts.tokenURLString);
//                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
//                httpsURLConnection.setRequestMethod("POST");
//                httpsURLConnection.setDoInput(true);
//                httpsURLConnection.setDoOutput(true);
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
//                outputStreamWriter.write("client_id=" + GlobalConsts.CLIENT_ID +
//                        "client_secret=" + GlobalConsts.CLIENT_SECRET +
//                        "grant_type=authorization_code" +
//                        "redirect_uri=" + GlobalConsts.CALLBACKURL +
//                        "code=" + request_token);
//                outputStreamWriter.flush();
//                String response = streamToString(httpsURLConnection.getInputStream());
//                JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();
//                accessTokenString = jsonObject.getString("access_token"); //Here is your ACCESS TOKEN
//                id = jsonObject.getJSONObject("user").getString("id");
//                username = jsonObject.getJSONObject("user").getString("username");
//                //This is how you can get the user info.
//                //You can explore the JSON sent by Instagram as well to know what info you got in a response
//
//
//                urlString = GlobalConsts.APIURL + "/users/" + id + "/media/recent/?access_token=" + accessTokenString;
//                url = new URL(urlString);
//                InputStream inputStream = url.openConnection().getInputStream();
//                response = streamToString(inputStream);
//
//                jsonObject = (JSONObject) new JSONTokener(response).nextValue();
//                JSONArray jsonArray = jsonObject.getJSONArray("data");
//                for (int i = 0; i < jsonArray.length(); i++)
//                {
//                    JSONObject mainImageJsonObject = jsonArray.getJSONObject(i).getJSONObject("images").getJSONObject("low_resolution");
//                    String imageUrlString = mainImageJsonObject.getString("url");
//                    Log.e("imageUrlString", "imageUrlString=" + imageUrlString);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        public String streamToString(InputStream p_is) {
//            try {
//                BufferedReader m_br;
//                StringBuffer m_outString = new StringBuffer();
//                m_br = new BufferedReader(new InputStreamReader(p_is));
//                String m_read = m_br.readLine();
//                while (m_read != null) {
//                    m_outString.append(m_read);
//                    m_read = m_br.readLine();
//                }
//                return m_outString.toString();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//}
//
//    String streamToString(InputStream is) {
//
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//
//        String line;
//        try {
//
//            br = new BufferedReader(new InputStreamReader(is));
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return sb.toString();
//
//    }
