package com.payoda.hopeline.feedback;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.GlobalConsts;
import com.payoda.hopeline.utils.utils.MultipartUtility;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class Recording extends Activity {
    private final String TAG = getClass().getName();
    private Button play, stop, record, send;
    private MediaRecorder myAudioRecorder;
    private String DeviceModel, DeviceName, Deviceversion;
    private String outputFile = null;
    private String url ="https://www.thehopeline.com/api/sendVoiceMessage.php";
    private ImageView imageview;
    private TextView textview,clicktext,clicktext1,stopclick,playclick,sendclick;
    private String name, email, contact;
    private ProgressDialog pDialog;
    private Context context = this;
    private  MediaPlayer player;
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classs);

        clicktext=(TextView)findViewById(R.id.clicktext);
        clicktext1=(TextView)findViewById(R.id.clicktext1);
        stopclick=(TextView)findViewById(R.id.clicktext2);
        playclick=(TextView)findViewById(R.id.clicktext3);
        sendclick=(TextView)findViewById(R.id.clicktext4);


        record = (Button) findViewById(R.id.record);
        final Intent intent = getIntent();
        intent.getExtras();

        requestPermission(Manifest.permission.RECORD_AUDIO,
                RECORD_REQUEST_CODE);

        name=intent.getStringExtra("name");
        email=intent.getStringExtra("email");
        contact=intent.getStringExtra("contact");

        Log.e("name is", "name is" + name+email+contact);


        if (GlobalConsts.footerLink_model.getDawson_voice().length()<80) {
            String temp =" ";
            for (int i = 0; i < (80- GlobalConsts.footerLink_model.getDawson_voice().length()); i++)
                temp = temp + " ";
            ((TextView) findViewById(R.id.layer_marquee).findViewById(R.id.markText)).setText(GlobalConsts.footerLink_model.getDawson_voice() + temp);

        } else
            ((TextView) findViewById(R.id.layer_marquee).findViewById(R.id.markText)).setText(GlobalConsts.footerLink_model.getDawson_voice());

        ((TextView) findViewById(R.id.layer_marquee).findViewById(R.id.markText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              if (!(GlobalConsts.footerLink_model.getDawson_voice_link().equals("")
                        || GlobalConsts.footerLink_model.getDawson_voice_link().equals("nolink")))
                {
                    Uri uri = Uri.parse(GlobalConsts.footerLink_model.getDawson_voice_link());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }

        });

       imageview = (ImageView) findViewById(R.id.img_back);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Recording.this, Activity_VoiceMsg_Form.class);
                startActivity(intent1);
                finish();
            }
        });

        textview = (TextView) findViewById(R.id.back);

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Recording.this, Activity_VoiceMsg_Form.class);
                startActivity(intent2);
                finish();
            }
        });


        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicktext.setVisibility(View.GONE);
                clicktext1.setVisibility(View.GONE);
                stopclick.setVisibility(View.GONE);
                playclick.setVisibility(View.GONE);
                sendclick.setVisibility(View.VISIBLE);

                new PostRequest().execute();
            }
        });
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);

        stop.setEnabled(false);


        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_REQUEST_CODE);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() +"/recording.AMR";

        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                STORAGE_REQUEST_CODE);




             myAudioRecorder = new MediaRecorder();
             myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
             myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
             myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);





        myAudioRecorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    clicktext.setVisibility(View.GONE);
                    clicktext1.setVisibility(View.VISIBLE);
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                record.setEnabled(false);
                stop.setEnabled(true);
                play.setEnabled(false);


            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicktext.setVisibility(View.GONE);
                clicktext1.setVisibility(View.GONE);
                stopclick.setVisibility(View.VISIBLE);


                    myAudioRecorder.stop();
                    myAudioRecorder.release();
                    myAudioRecorder = null;

                    stop.setEnabled(false);
                    play.setEnabled(true);
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {

                clicktext.setVisibility(View.GONE);
                clicktext1.setVisibility(View.GONE);
                stopclick.setVisibility(View.GONE);
                playclick.setVisibility(View.VISIBLE);

                if (player != null && player.isPlaying())
                {
                    player.release();
                }

                player = new MediaPlayer();

                try {
                    player.setDataSource(outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    player.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                player.start();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    public void getSizeinMB() {
        File file = new File(outputFile);

        long sizeInBytes = file.length();

        long sizeInMb = sizeInBytes / (1024 * 1024);

        Log.e("size in mb is","size in mb is"+sizeInMb);
    }

    private class PostRequest extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Recording.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();


        }

        @SuppressWarnings("deprecation")
        @Override
        protected String doInBackground(Void... arg0) {

            DeviceModel = android.os.Build.MODEL;
            DeviceName = android.os.Build.MANUFACTURER;
            Deviceversion = Build.VERSION.RELEASE;

            Log.e("Device is", "device is" + DeviceModel + " " + DeviceName + " " + Deviceversion);

            String responseString = null;
            try {
                MultipartUtility multipartEntity = new MultipartUtility(url,"utf-8");
                multipartEntity.addFormField("name", name);
                multipartEntity.addFormField("email",email);
                multipartEntity.addFormField("phone",contact);
                multipartEntity.addFormField("device",DeviceModel+" "+DeviceName +" "+Deviceversion);
                multipartEntity.addFilePart("file",new File(outputFile));


                List<String> response = multipartEntity.finish();
                Log.e(TAG, "SERVER REPLIED:");
                for (String line : response) {
                    Log.e(TAG, "Upload Files Response:::" + line);
                    // get your server response here.
                    responseString = line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

            if (pDialog != null && pDialog.isShowing())
                pDialog.dismiss();

            Log.e(TAG, "Response from server::\n" + aVoid);
            // {"status":"success","message":"Email sent successfully."}

            try {
                JSONObject object = new JSONObject(aVoid);
                if (object.getString("status").equals("success"))
                {
                    Toast.makeText(context,object.getString("message"),Toast.LENGTH_SHORT).show();
                    finish();
                }
                else

                    Toast.makeText(context,"Sending failed,Please try again.",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                //NullPointerException coming
                e.printStackTrace();
            }


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