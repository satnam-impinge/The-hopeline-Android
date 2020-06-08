package com.payoda.hopeline.listen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.payoda.hopeline.MyApplication;
import com.payoda.hopeline.R;
import com.payoda.hopeline.feedback.Activity_VoiceMsg_Form;
import com.payoda.hopeline.get_encouraged.InstagramFeed;
import com.payoda.hopeline.main.ActivityWebView;
import com.payoda.hopeline.utils.AppUtils;
import com.payoda.hopeline.utils.CustomWebview;
import com.payoda.hopeline.utils.GlobalConsts;
import com.payoda.hopeline.utils.MyMediaPlayer1;
import com.payoda.hopeline.utils.MyMediaPlayer2;

import java.lang.reflect.InvocationTargetException;


public class ActivityListen extends Activity implements MediaPlayer.OnErrorListener, View.OnClickListener {

    private ImageView player1, player2;
    private ProgressBar progress1, progress2,progress_web;
    private CustomWebview webView_player;

    private MediaPlayer mPlayer1, mPlayer2;
    private RelativeLayout linr_live_demand;

    private Context context = this;
    TextView footer;


//    String url = "http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listen);
        AppUtils.setScreenName(this, "Dawson Online");

        GlobalConsts.initBack(this);

      // webView_player=(CustomWebview) findViewById(R.id.webView_player);
        linr_live_demand=(RelativeLayout) findViewById(R.id.rel_live_demand);
     //   progress_web=(ProgressBar) findViewById(R.id.progress_web);
        findViewById(R.id.call_the_show).setOnClickListener(this);
        findViewById(R.id.helped).setOnClickListener(this);
        findViewById(R.id.find_a_station).setOnClickListener(this);
        findViewById(R.id.img_join_the_converstation).setOnClickListener(this);
        findViewById(R.id.img_follow).setOnClickListener(this);

        ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText(getResources().getString(R.string.dawson_online));


        footer=(TextView)findViewById(R.id.bottomMark).findViewById(R.id.markText);
        footer.setSelected(true);

        if (GlobalConsts.footerLink_model.getListen_screen().length() < 80) {
            String temp = " ";
            for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getListen_screen().length()); i++)
                temp = temp + " ";
            footer.setText(GlobalConsts.footerLink_model.getListen_screen() + temp);

        } else
            footer.setText(GlobalConsts.footerLink_model.getListen_screen());

        linr_live_demand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ActivityListen.this, ActivityWebView.class);
                intent.putExtra("url",GlobalConsts.DMLive_OnDemand);
                intent.putExtra(GlobalConsts.EXTRA_TITLE,"DMLive ON DEMAND");
                startActivity(intent);
            }
        });


        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(GlobalConsts.footerLink_model.getListen_screen_link().equals("")
                        || GlobalConsts.footerLink_model.getListen_screen_link().equals("nolink"))) {

                    Uri uri = Uri.parse(GlobalConsts.footerLink_model.getListen_screen_link());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });




//=========================web_view_player======================
       /* webView_player.setVerticalScrollBarEnabled(true);
        webView_player.setHorizontalScrollBarEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView_player.getSettings().setMediaPlaybackRequiresUserGesture(true);
        }


        webView_player.getSettings().setJavaScriptEnabled(true);
        webView_player.loadUrl("https://www.thehopeline.com/embed-libsyn-player-on-webview/");


        webView_player.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
                Log.e("time", "Progress " + progress);
              *//*  if (progress >= 89 && !isStarted)
                {
                    startDate = new Date();
                    isStarted = true;
                    pb.setProgress(Window.PROGRESS_END);
                    pb.setVisibility(View.GONE);
                }
                pb.setProgress(progress);*//*
            }

        });

        webView_player.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {

                Log.e("weburl","......."+url);
                *//** This prevents the loading of pages in system browser *//*
                return false;
            }

            *//** Callback method, executed when the page is completely loaded *//*
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                *//** Progress reaches to 100% when progress value
                 * is Window.PROGRESS_END (10000) *//*
                Log.e("started","started");
                progress_web.setProgress(Window.PROGRESS_END);
                progress_web.setVisibility(View.GONE);

            }

        });

*/


//        WebView view1 = (WebView) findViewById(R.id.webView_footer);
//        view1.getSettings().setLoadsImagesAutomatically(true);
//        view1.getSettings().setJavaScriptEnabled(true);
//        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        view1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-listen/");

        mPlayer1 = MyMediaPlayer1.getMediaPlayer1();
      //  mPlayer2 = MyMediaPlayer2.getMediaPlayer2();
        mPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mPlayer2.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer1.setDataSource(context, Uri.parse(GlobalConsts.DMLive));
            //IllegalStateException is coming
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
        //    mPlayer2.setDataSource(context, Uri.parse( GlobalConsts.HotLine));
//            mPlayer2.setDataSource("http://www.virginmegastore.me/Library/Music/CD_001214/Tracks/Track1.mp3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (GlobalConsts.should_prepare) {
            mPlayer1.prepareAsync();
      //      mPlayer2.prepareAsync();
            GlobalConsts.should_prepare = false;
        }

        mPlayer1.setOnErrorListener(this);
      //  mPlayer2.setOnErrorListener(this);

        mPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                GlobalConsts.is_mP1_ready = true;
                if (GlobalConsts.is_mP1_perparing) {
                    mPlayer1.start();
//                    GlobalConsts.is_mP1_perparing=false;
                }
                if (progress1.getVisibility() == View.VISIBLE)
                    progress1.setVisibility(View.GONE);
            }
        });
       /* mPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                GlobalConsts.is_mP2_ready = true;
                if (GlobalConsts.is_mP2_perparing) {
                    mPlayer2.start();
//                    GlobalConsts.is_mP2_perparing=false;
                }
                if (progress2.getVisibility() == View.VISIBLE)
                    progress2.setVisibility(View.GONE);
            }
        });*/


        player1 = (ImageView) findViewById(R.id.player_1);
        player1.setTag(GlobalConsts.Player1_tag);
        //player2 = (ImageView) findViewById(R.id.player_2);
        //player2.setTag(GlobalConsts.Player2_tag);

        player1.setOnClickListener(this);
       // player2.setOnClickListener(this);

        progress1 = (ProgressBar) findViewById(R.id.progress_1);
        progress1.setVisibility(View.GONE);
        //progress2 = (ProgressBar) findViewById(R.id.progress_2);
        //progress2.setVisibility(View.GONE);

        /*findViewById(R.id.join_tribe).setOnClickListener(this);*/


        if(!player1.getTag().equals(GlobalConsts.PAUSE)) {
            player1.setImageResource(R.mipmap.pause);

            if(GlobalConsts.is_mP1_perparing && !GlobalConsts.is_mP1_ready)
                progress1.setVisibility(View.VISIBLE);
        }
       /* if(!player2.getTag().equals(GlobalConsts.PAUSE)) {
            player2.setImageResource(R.mipmap.pause);

            if(GlobalConsts.is_mP2_perparing && !GlobalConsts.is_mP2_ready)
                progress2.setVisibility(View.VISIBLE);
        }*/


    }



    private void Pause1() {
        if (mPlayer1 != null && mPlayer1.isPlaying()) {
            mPlayer1.pause();
        }
    }

   /* private void Pause2() {
        if (mPlayer2 != null && mPlayer2.isPlaying()) {
            mPlayer2.pause();
        }
    }*/


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {

        if (progress1.getVisibility() == View.VISIBLE)
            progress1.setVisibility(View.GONE);

        if (progress2.getVisibility() == View.VISIBLE)
            progress2.setVisibility(View.GONE);

        return false;
    }
    Intent intent;

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(final View v) {

        switch (v.getId()) {
            case R.id.helped:
             /*   Intent inn=new Intent(ActivityListen.this, Activity_VoiceMsg_Form.class);
                startActivity(inn);
                finish();*/
                intent = new Intent(ActivityListen.this, ActivityWebView.class);
                intent.putExtra("url","https://www.thehopeline.com/get-dawson-texts/");
                intent.putExtra(GlobalConsts.EXTRA_TITLE,"Get Dawson Texts");
                startActivity(intent);


                break;


            case R.id.call_the_show:
              /*  Intent intent=new Intent(ActivityListen.this,CallTheShow.class);
                startActivity(intent);
                finish();*/
                intent = new Intent(ActivityListen.this, ActivityWebView.class);
                intent.putExtra("url","https://www.thehopeline.com/askdawson/");
                intent.putExtra(GlobalConsts.EXTRA_TITLE,"Ask Dawson");
                startActivity(intent);

                break;

            case R.id.find_a_station:
                intent = new Intent(ActivityListen.this, ActivityWebView.class);
                intent.putExtra("url","https://www.thehopeline.com/podcast-list/");
                intent.putExtra(GlobalConsts.EXTRA_TITLE,"Podcasts");
                startActivity(intent);
                v.setBackgroundColor(getResources().getColor(R.color.phone_color));
                break;

            case R.id.img_join_the_converstation:
                intent = new Intent(ActivityListen.this, ActivityWebView.class);
                intent.putExtra("url","https://www.facebook.com/DawsonMcAllister");
                intent.putExtra(GlobalConsts.EXTRA_TITLE,"JOIN THE CONVERSATION");
                startActivity(intent);
               // v.setBackgroundColor(getResources().getColor(R.color.converstation_color));
                break;
            case R.id.img_follow:

                intent = new Intent(context, InstagramFeed.class);
//                intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"get-twitter-feeds-mobile-app/");
                intent.putExtra(GlobalConsts.EXTRA_URL, GlobalConsts.URL+"get-twitter-feeds-dawson-mobile-app/");
                intent.putExtra(GlobalConsts.EXTRA_TITLE, "Twitter Feed");
                startActivity(intent);
                //v.setBackgroundColor(getResources().getColor(R.color.twitter_color));
                break;


            case R.id.player_1:


                if (player1.getTag().equals(GlobalConsts.PAUSE)) {
                    if (GlobalConsts.is_mP1_ready) {
                        mPlayer1.start();
                    } else {
                        GlobalConsts.is_mP1_perparing = true;
                        progress1.setVisibility(View.VISIBLE);
                    }
                    GlobalConsts.is_mP2_perparing = false;
                    player1.setTag(GlobalConsts.PLAYING);
                    GlobalConsts.Player1_tag=GlobalConsts.PLAYING;
                    player1.setImageResource(R.mipmap.pause);
                   // player2.setTag(GlobalConsts.PAUSE);
                    //GlobalConsts.Player2_tag=GlobalConsts.PAUSE;
                  //  player2.setImageResource(R.mipmap.play);
                   // if (GlobalConsts.is_mP2_ready)
                        //Pause2();
                } else {
                    GlobalConsts.is_mP1_perparing = false;
                    player1.setImageResource(R.mipmap.play);
                    player1.setTag(GlobalConsts.PAUSE);
                    GlobalConsts.Player1_tag=GlobalConsts.PAUSE;
                    progress1.setVisibility(View.GONE);
                    Pause1();
                }
               /* if (progress2.getVisibility() == View.VISIBLE)
                    progress2.setVisibility(View.GONE);
*/
                break;


        /*    case R.id.player_2:
                if (player2.getTag().equals(GlobalConsts.PAUSE)) {
                    if (GlobalConsts.is_mP2_ready) {
                        mPlayer2.start();
                    } else {
                        GlobalConsts.is_mP2_perparing = true;
                        progress2.setVisibility(View.VISIBLE);
                    }
                    GlobalConsts.is_mP1_perparing=false;
                    player2.setTag(GlobalConsts.PLAYING);
                    GlobalConsts.Player2_tag=GlobalConsts.PLAYING;
                    player2.setImageResource(R.mipmap.pause);
                    player1.setTag(GlobalConsts.PAUSE);
                    GlobalConsts.Player1_tag=GlobalConsts.PAUSE;
                    player1.setImageResource(R.mipmap.play);
                    if (GlobalConsts.is_mP1_ready)
                        Pause1();
                } else {
                    GlobalConsts.is_mP2_perparing = false;
                    player2.setImageResource(R.mipmap.play);
                    player2.setTag(GlobalConsts.PAUSE);
                    GlobalConsts.Player2_tag=GlobalConsts.PAUSE;
                    progress2.setVisibility(View.GONE);
                    Pause2();
                }

                if (progress1.getVisibility() == View.VISIBLE)
                    progress1.setVisibility(View.GONE);

                break;*/


           /* case R.id.join_tribe:
                v.setBackgroundColor(getResources().getColor(R.color.grey));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(
                                new Intent(context, ActivityWebView.class)
                                        .putExtra(GlobalConsts.EXTRA_URL,"http://info.thehopeline.com/join-dawsons-tribe")
                                        .putExtra(GlobalConsts.EXTRA_TITLE,"Join Tribe"));
                        v.setBackgroundColor(getResources().getColor(R.color.chat_green));
                    }
                }, 150);

                break;*/

            default:
                break;
        }
    }
}

