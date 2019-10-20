package com.example.cantpickup;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class IncomingCallInterceptor extends Activity {

    private IntentFilter filter = new IntentFilter();
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                onIncomingCall();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(broadcastReceiver, filter);
    }

    private void onIncomingCall() {
        //TODO add in check for permissions
        startService(new Intent(IncomingCallInterceptor.this, PopUpService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //TODO sort out the unregister error loop into the background service
        //https://stackoverflow.com/questions/14357566/activity-has-leaked-intentreceiver-that-was-originally-registered-here-are-you
     //  unregisterReceiver(broadcastReceiver);
    }

}
