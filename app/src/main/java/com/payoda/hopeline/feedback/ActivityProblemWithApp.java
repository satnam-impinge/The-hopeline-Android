package com.payoda.hopeline.feedback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.GlobalConsts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shivam on 24/2/16.
 */
public class ActivityProblemWithApp extends Activity {

    EditText email_address, phone_number, device, manufacturer, device_model, cellular_srvice_provider, device_os,
            time_of_prob, device_type, details_of_issue;
    TextView submit;
    String msg;

    TextView footer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_problemwithapp);
        GlobalConsts.initBack(this);
        findViewById(R.id.heading).setVisibility(View.GONE);
        email_address = (EditText) findViewById(R.id.email_address);
        phone_number = (EditText) findViewById(R.id.phone_number);
        device = (EditText) findViewById(R.id.device);
        manufacturer = (EditText) findViewById(R.id.manufacturer);
        device_model = (EditText) findViewById(R.id.device_model);
        cellular_srvice_provider = (EditText) findViewById(R.id.cellular_srvice_provider);
        device_os = (EditText) findViewById(R.id.device_os);
        time_of_prob = (EditText) findViewById(R.id.time_of_prob);
        device_type = (EditText) findViewById(R.id.device_type);
        details_of_issue = (EditText) findViewById(R.id.details_of_issue);

        device_model.setText(android.os.Build.MODEL);
        device_os.setText("Android " + android.os.Build.VERSION.RELEASE);
        manufacturer.setText(Build.MANUFACTURER);
        device.setText(Build.DEVICE);
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        cellular_srvice_provider.setText(manager.getNetworkOperatorName());

        WebView webView = new WebView(getApplicationContext());
        device_type.setText(webView.getSettings().getUserAgentString());

        (findViewById(R.id.layer_marquee)).findViewById(R.id.markText).setSelected(true);

        footer = (TextView) findViewById(R.id.bottomMark).findViewById(R.id.markText);

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


        submit = (TextView) findViewById(R.id.save);
        submit.setVisibility(View.VISIBLE);
        submit.setText("Submit");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email_address.getText() != null && !email_address.getText().toString().trim().equals("")) {
                    if (isValidEmail(email_address.getText().toString().trim())) {
                        if (details_of_issue.getText() != null && !details_of_issue.getText().toString().trim().equals("")) {
                            alert("TheHopeLine says...", "Kindly make sure to submit the form only via email.", true);
                        } else {
                            alert("Please fill in all required fields", "Details of the issue is mandatory!", false);
                        }

                    } else {
                        alert("Invalid email address!", "Your Email-address is not valid!", false);
                    }

                } else {
                    alert("Please fill in all required fields", "Email is mandatory!", false);
                }

            }
        });


    }

    public boolean isValidEmail(String enteredEmail) {
//        String EMAIL_REGIX = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
//        Pattern pattern = Pattern.compile(EMAIL_REGIX);
//        Matcher matcher = pattern.matcher(enteredEmail);
//        return ((!enteredEmail.isEmpty()) && (matcher.matches()));

        return android.util.Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches();
    }

    private void alert(String title, String msg, final boolean sendEmail) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (sendEmail)
                    sendEmail();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    protected void sendEmail() {
        // email: thlmobileerror@thehopeline.com
        // sub: The Hopeline Mobile App Error Message


        msg = "Model: " + device_model.getText() + "\r\n \r\n" + "OperatingSystem: " + device_os.getText() + "\r\n \r\n" + "Browser: " + device_type.getText() + "\r\n \r\n";
        msg = msg + "Email: " + email_address.getText() + "\r\n \r\n" + "IssueDetails: " + details_of_issue.getText() + "\r\n \r\n";
        if (phone_number.getText() != null && !phone_number.getText().toString().trim().equals("")) {
            msg = msg + "PhoneNumber: " + phone_number.getText() + "\r\n \r\n";
        }
        if (device.getText() != null && !device.getText().toString().trim().equals("")) {
            msg = msg + "Device: " + device.getText() + "\r\n \r\n";
        }
        if (manufacturer.getText() != null && !manufacturer.getText().toString().trim().equals("")) {
            msg = msg + "Manufacturer: " + manufacturer.getText() + "\r\n \r\n";
        }
        if (cellular_srvice_provider.getText() != null && !cellular_srvice_provider.getText().toString().trim().equals("")) {
            msg = msg + "ServiceProvider: " + cellular_srvice_provider.getText() + "\r\n \r\n";
        }
        if (time_of_prob.getText() != null && !time_of_prob.getText().toString().trim().equals("")) {
            msg = msg + "Time: " + time_of_prob.getText() + "\r\n \r\n";
        }


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto: thlmobilesurvey@thehopeline.com"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"thlmobileerror@thehopeline.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "The Hopeline Mobile App Error Message");
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (Exception ex) {
        }
    }
}
