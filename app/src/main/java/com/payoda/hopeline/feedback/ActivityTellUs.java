package com.payoda.hopeline.feedback;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.AppUtils;
import com.payoda.hopeline.utils.GlobalConsts;

/**
 * Created by shivam on 25/2/16.
 */
public class ActivityTellUs extends Activity implements View.OnClickListener {


    String contact_by ="Telephone";
    TextView select_q1, select_q2, select_q3, select_q4, submit;

    TextView footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tellus);

        AppUtils.setScreenName(this, "Feedback");

        GlobalConsts.initBack(this);
        select_q1 = (TextView) findViewById(R.id.select_q1);
        select_q1.setOnClickListener(this);
        select_q2 = (TextView) findViewById(R.id.select_q2);
        select_q2.setOnClickListener(this);
        select_q3 = (TextView) findViewById(R.id.select_q3);
        select_q3.setOnClickListener(this);
        select_q4 = (TextView) findViewById(R.id.select_q4);
        select_q4.setOnClickListener(this);
        submit = (TextView) findViewById(R.id.save);
        submit.setVisibility(View.VISIBLE);
        submit.setText("Submit");
        submit.setOnClickListener(this);
        findViewById(R.id.heading).setVisibility(View.GONE);
        findViewById(R.id.telephone).setOnClickListener(this);
        findViewById(R.id.online_chat).setOnClickListener(this);
        findViewById(R.id.txt_msg).setOnClickListener(this);
        (findViewById(R.id.layer_marquee)).findViewById(R.id.markText).setSelected(true);
        footer=(TextView)findViewById(R.id.bottomMark).findViewById(R.id.markText);

        if (GlobalConsts.footerLink_model.getFeedback_screen().length() < 80) {
            String temp = " ";
            for (int i = 0; i < (80 - GlobalConsts.footerLink_model.getFeedback_screen().length()); i++)
                temp = temp + " ";
            footer.setText(GlobalConsts.footerLink_model.getFeedback_screen() + temp);

        } else
            footer.setText(GlobalConsts.footerLink_model.getFeedback_screen());

        footer.setOnClickListener(new View.OnClickListener() {
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
//        view1.loadUrl(GlobalConsts.bete_URL + "mobile-app-survey-page-marquee/");


    }

    Dialog d;

    public Dialog onCreateDialogSingleChoice(final TextView tv) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] array = {"Select","Strongly Agree", "Agree", "I Guess So", "Disagree", "Strongly Disagree"};
        builder.setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText(array[which]);
                d.dismiss();

            }
        });

        d = builder.create();

        return d;
    }


    protected void sendEmail(String msg) {


        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:thlmobilesurvey@thehopeline.com"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"thlmobilesurvey@thehopeline.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "The Hopeline Mobile App Survey");
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

        try {
            startActivity(Intent.createChooser(emailIntent,"Send mail..."));
            finish();
        } catch (Exception ex) {
        }
    }

    @Override
    public void onClick(View v) {
        Dialog dialog = null;
        switch (v.getId()) {
            case R.id.select_q1:
                dialog = onCreateDialogSingleChoice(select_q1);
                dialog.show();
                break;
            case R.id.select_q2:
                dialog = onCreateDialogSingleChoice(select_q2);
                dialog.show();
                break;

            case R.id.select_q3:
                dialog = onCreateDialogSingleChoice(select_q3);
                dialog.show();
                break;
            case R.id.select_q4:
                dialog = onCreateDialogSingleChoice(select_q4);
                dialog.show();
                break;
            case R.id.telephone:
                contact_by = "Telephone";
                findViewById(R.id.telephone_tik).setVisibility(View.VISIBLE);
                findViewById(R.id.online_chat_tik).setVisibility(View.GONE);
                findViewById(R.id.txt_msg_tik).setVisibility(View.GONE);
                break;
            case R.id.online_chat:
                contact_by = "Online Chat";
                findViewById(R.id.telephone_tik).setVisibility(View.GONE);
                findViewById(R.id.online_chat_tik).setVisibility(View.VISIBLE);
                findViewById(R.id.txt_msg_tik).setVisibility(View.GONE);
                break;
            case R.id.txt_msg:
                contact_by = "Text Messaging";
                findViewById(R.id.telephone_tik).setVisibility(View.GONE);
                findViewById(R.id.online_chat_tik).setVisibility(View.GONE);
                findViewById(R.id.txt_msg_tik).setVisibility(View.VISIBLE);
                break;
            case R.id.save:

                String msg="\r\n";

                if (!select_q1.getText().toString().equals("Select")) {
                    msg = msg + getResources().getString(R.string.tellus_desc_1) + "\r\n" + select_q1.getText().toString()+ "\r\n"+ "\r\n";
                }
                if (!select_q2.getText().toString().equals("Select")) {
                    msg = msg + getResources().getString(R.string.tellus_que_1) + "\r\n" + select_q2.getText().toString()+ "\r\n"+ "\r\n";
                }
                if (!select_q3.getText().toString().equals("Select")) {
                    msg = msg + getResources().getString(R.string.tellus_que_2) + "\r\n" + select_q3.getText().toString()+ "\r\n"+ "\r\n";
                }
                if (!select_q4.getText().toString().equals("Select")) {
                    msg = msg + getResources().getString(R.string.tellus_que_3) + "\r\n" + select_q4.getText().toString()+ "\r\n"+ "\r\n";
                }

                msg = msg + getResources().getString(R.string.tellus_que_4) + "\r\n"+contact_by+ "\r\n"+ "\r\n";

                alert(msg);

                break;

        }

    }

    private void alert(final String msg) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("TheHopeLine says...");
        alertDialogBuilder.setMessage("Kindly make sure to submit the form only via email.");

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                sendEmail(msg);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}

