package com.payoda.hopeline.feedback;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.payoda.hopeline.MyApplication;
import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.AppUtils;
import com.payoda.hopeline.utils.GlobalConsts;


public class Activity_VoiceMsg_Form extends Activity implements View.OnClickListener {

    private Context context=this;

    EditText name, email, contact_number;

    TextView image_back;
    ImageView back;
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_voice_msg_form);

        AppUtils.setScreenName(this, "Voice Message");

        GlobalConsts.initBack(this);

        requestPermission(Manifest.permission.RECORD_AUDIO,
                RECORD_REQUEST_CODE);

        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_REQUEST_CODE);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);

        contact_number =(EditText)findViewById(R.id.contact_number);

        image_back=(TextView)findViewById(R.id.back);

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


        findViewById(R.id.start_recording).setOnClickListener(this);


    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(final View v) {

        if (name.length() > 0 && email.length() > 0 && contact_number.length() > 0) {
            Intent intent = new Intent(this, Recording.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("contact", contact_number.getText().toString());
            startActivity(intent);



            /*name.setText("");
            email.setText("");
            contact_number.setText("");*/
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your details", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RECORD_REQUEST_CODE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {



                }
                return;
            }
            case STORAGE_REQUEST_CODE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {


                }
                return;
            }
        }
    }

    protected void requestPermission(String permissionType, int requestCode) {
        int permission = ContextCompat.checkSelfPermission(this,
                permissionType);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permissionType}, requestCode
            );
        }
    }
}
