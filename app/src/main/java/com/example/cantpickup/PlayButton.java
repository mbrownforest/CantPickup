package com.example.cantpickup;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class PlayButton extends android.support.v7.widget.AppCompatButton {

    public MediaPlayer player = null;
    boolean mStartPlaying = true;
    private String fileName = null;

    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onPlay(mStartPlaying);
            if (mStartPlaying) {
                setText("Stop playing");
            } else {
                setText("Start playing");
            }
            mStartPlaying = !mStartPlaying;
        }
    };

    public PlayButton(Context ctx) {
        super(ctx);
        setText("Start playing");
        setOnClickListener(clicker);
    }


    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e("ERR0R", "prepare() failed");
        }
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }

}
