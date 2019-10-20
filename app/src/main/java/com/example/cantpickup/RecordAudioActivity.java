package com.example.cantpickup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class RecordAudioActivity extends AppCompatActivity {

    private RecordButton recordButton = null;
    private PlayButton   playButton = null;

    String fileName;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Record to the external cache directory for visibility
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/cantpickupmessage.3gp";

        LinearLayout recordingScreen = new LinearLayout(this);
        recordButton = new RecordButton(this);
        recordingScreen.addView(recordButton,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        0));
        playButton = new PlayButton(this);
        recordingScreen.addView(playButton,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        0));
        setContentView(recordingScreen);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recordButton.recorder != null) {
            recordButton.recorder.release();
            recordButton.recorder = null;
        }

        if (playButton.player != null) {
            playButton.player.release();
            playButton.player = null;
        }
    }


}
