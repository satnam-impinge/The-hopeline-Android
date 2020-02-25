package com.payoda.hopeline.utils;

import android.media.MediaPlayer;

/**
 * Created by shivam on 22/2/16.
 */
public class MyMediaPlayer1 extends MediaPlayer {

    private static MediaPlayer mPlayer1= new MediaPlayer();

    public static MediaPlayer getMediaPlayer1( ) {
        return mPlayer1;
    }

}
