package com.payoda.hopeline.feedback;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.payoda.hopeline.R;
import com.payoda.hopeline.utils.GlobalConsts;
import com.payoda.hopeline.utils.WaveformView;

import java.io.IOException;

public class Activity_Recording extends Activity
{
    ImageButton play,  record;
    boolean  isPlaying;

    private WaveformView mRealtimeWaveformView;
    private RecordingThread mRecordingThread;

//    private VisualizerView mVisualizerView;

    private String outputFile = null;
    private static final int REQUEST_RECORD_AUDIO = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording);



        GlobalConsts.initBack(this);

        ((TextView) findViewById(R.id.head).findViewById(R.id.heading)).setText("Feedback");


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


        play = (ImageButton) findViewById(R.id.play);
        record = (ImageButton) findViewById(R.id.record);

        play.setEnabled(false);

        ;


//        mVisualizerView = (VisualizerView) findViewById(R.id.visualizer);
//        ViewTreeObserver observer = mVisualizerView.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mVisualizerView.setBaseY(mVisualizerView.getHeight());
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    mVisualizerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                } else {
//                    mVisualizerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
//            }
//        });

        mRealtimeWaveformView = (WaveformView) findViewById(R.id.waveformView);
        mRecordingThread = new RecordingThread(new AudioDataReceivedListener() {
            @Override
            public void onAudioDataReceived(short[] data) {
                mRealtimeWaveformView.setSamples(data);
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!mRecordingThread.recording()) {
                        startAudioRecordingSafe();
                        play.setEnabled(false);
                        record.setImageResource(android.R.drawable.ic_media_pause);
                    } else {
                        record.setImageResource(android.R.drawable.presence_audio_away);
                        mRecordingThread.stopRecording();
                        play.setEnabled(true);
                    }
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException, SecurityException, IllegalStateException {
                MediaPlayer m = new MediaPlayer();

                try {
                    outputFile = Environment.getExternalStorageDirectory().getPath()+"/Recording.WAV";

                    m.setDataSource(outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    m.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                m.start();
                Toast.makeText(getApplicationContext(), "Playing audio", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startAudioRecordingSafe() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {
            mRecordingThread.startRecording();
        } else {
            requestMicrophonePermission();
        }
    }

    private void requestMicrophonePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)) {
            // Show dialog explaining why we need record audio
            Snackbar.make(mRealtimeWaveformView, "Microphone access is required in order to record audio",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(Activity_Recording.this, new String[]{
                            android.Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO);
                }
            }).show();
        } else {
            ActivityCompat.requestPermissions(Activity_Recording.this, new String[]{
                    android.Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_RECORD_AUDIO && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mRecordingThread.stopRecording();

        }
    }
}