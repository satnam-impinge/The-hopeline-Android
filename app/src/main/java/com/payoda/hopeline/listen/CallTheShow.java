package com.payoda.hopeline.listen;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.payoda.hopeline.R;
import com.payoda.hopeline.feedback.Activity_VoiceMsg_Form;

/**
 * Created by shivam on 9/2/2016.
 */
public class CallTheShow extends Activity implements View.OnClickListener {
LinearLayout linearLayout_if_the_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_the_show);

        ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText("Call The Show");
        findViewById(R.id.call1).setOnClickListener(this);
        findViewById(R.id.cal2).setOnClickListener(this);
        linearLayout_if_the_show = (LinearLayout)findViewById(R.id.linearLayout_if_the_show);
        linearLayout_if_the_show.setOnClickListener(this);
        RelativeLayout img_back = (RelativeLayout) findViewById(R.id.head);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},111);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.call1:
                break;
case R.id.linearLayout_if_the_show:
    Intent callIntent1 = new Intent(this, Activity_VoiceMsg_Form.class);
    startActivity(callIntent1);

                break;

            case R.id.cal2:

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + "+18003944673"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},111);

                    return;
                }
                startActivity(callIntent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 111){

            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

//                Toast.makeText(this,"Please click on call button to call!",Toast.LENGTH_LONG).show();
            }
        }
    }

}
