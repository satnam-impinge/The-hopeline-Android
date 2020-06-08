package com.payoda.hopeline.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.livechatinc.inappchat.ChatWindowActivity;
import com.payoda.hopeline.MyApplication;
import com.payoda.hopeline.R;
import com.payoda.hopeline.get_encouraged.InstagramFeed;
import com.payoda.hopeline.main.ActivityWebView;
import com.payoda.hopeline.utils.AppUtils;
import com.payoda.hopeline.utils.GlobalConsts;


public class ActivityGetHelp extends Activity implements View.OnClickListener
{
    private Context context=this;
    TextView footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gethelp);

        AppUtils.setScreenName(this, "Get Help");

        GlobalConsts.initBack(this);

        findViewById(R.id.chat_with_us).setOnClickListener(this);
        findViewById(R.id.stories).setOnClickListener(this);
       /* findViewById(R.id.call_us).setOnClickListener(this);*/
        findViewById(R.id.blog).setOnClickListener(this);
        findViewById(R.id.request).setOnClickListener(this);
        findViewById(R.id.eBooks).setOnClickListener(this);
        findViewById(R.id.follow).setOnClickListener(this);

        ((TextView)findViewById(R.id.head).findViewById(R.id.heading)).setText("Get Help");

        footer=(TextView)findViewById(R.id.bottomMark).findViewById(R.id.markText);
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



//        WebView view1 = (WebView) findViewById(R.id.webView_footer);
//        view1.getSettings().setLoadsImagesAutomatically(true);
//        view1.getSettings().setJavaScriptEnabled(true);
//        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        view1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-marquee/");

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(final View v) {

        v.setBackgroundColor(getResources().getColor(R.color.grey));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                switch (v.getId())
                {
                    case R.id.chat_with_us:

//                        intent = new Intent(context, ActivityWebView.class);
//                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"mobile-signup");
//                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Chat with Us");
//                        startActivity(intent);


                        intent = new Intent(context,ChatWindowActivity.class);
                        intent.putExtra(ChatWindowActivity.KEY_GROUP_ID, "16");
                        intent.putExtra(ChatWindowActivity.KEY_LICENCE_NUMBER, "6982601");
//                        intent.putExtra(ChatWindowActivity.KEY_VISITOR_NAME, "Client");
//                        intent.putExtra(ChatWindowActivity.KEY_VISITOR_EMAIL, "client@example.com");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.green_demanded));

                        break;

                    case R.id.stories:
                        intent = new Intent(context, ActivityWebView.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"storiespage/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Stories");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.stories));
                        break;
                    case R.id.follow:
                        intent = new Intent(context, ActivityWebView.class);
//                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"storiespage/");
//                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Stories");
//                        startActivity(intent);
//                        v.setBackgroundColor(getResources().getColor(R.color.stories));

//                        intent = new Intent(context, InstagramFeed.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"get-twitter-feeds-mobile-app/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Twitter Feed");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.twitter_color));
                        break;

                  /*  case R.id.call_us:

                        intent = new Intent(context, ActivityCallus.class);
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.stories));

                        break;*/

                    case R.id.blog:
                        intent = new Intent(context, ActivityWebView.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"tag-list-mobile/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "Topics");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.blog_color));
                        break;

                    case R.id.request:
                        intent = new Intent(context, ActivityEmailMentor.class);
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.phone_color));
                        break;

                    case R.id.eBooks:
                        intent = new Intent(context, ActivityWebView.class);
                        intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"ebooks-for-iphone-ipad-android/");
                        intent.putExtra(GlobalConsts.EXTRA_TITLE, "eBooks");
                        startActivity(intent);
                        v.setBackgroundColor(getResources().getColor(R.color.ebooks_color));
                        break;
                    default:

                        break;
                }
            }
        }, 150);

    }
}
