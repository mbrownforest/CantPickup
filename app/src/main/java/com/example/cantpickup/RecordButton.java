package com.example.cantpickup;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class RecordButton extends android.support.v7.widget.AppCompatButton {

    boolean mStartRecording = true;
    public MediaRecorder recorder = null;
    private String fileName = null;

    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onRecord(mStartRecording);
            if (mStartRecording) {
                setText("Stop recording");
            } else {
                setText("Start recording");
            }
            mStartRecording = !mStartRecording;
        }
    };

    public RecordButton(Context ctx) {
        super(ctx);
        setText("Start recording");
        setOnClickListener(clicker);
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e("ERROR", "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording () {
        recorder.stop();
        recorder.release();
        recorder = null;
    }

}
