package com.example.cantpickup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class ServiceRequests extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1963;
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 1995;
    private static final int MY_PERMISSIONS_REQUEST_SYSTEM_ALERT_WINDOW = 1401;

    private boolean permissionOverdrawGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForOverdrawPermission();
        checkForPhoneStatePermission();
        Intent callIntent = new Intent(this, IncomingCallInterceptor.class);
        startActivity(callIntent);
    }

    private void checkForPhoneStatePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("YE PERMISSION BE NOT ", "GRANTED");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
            }

        }
    }

    private void checkForOverdrawPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("YE PERMISSION BE NOT ", "GRANTED");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SYSTEM_ALERT_WINDOW)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                        MY_PERMISSIONS_REQUEST_SYSTEM_ALERT_WINDOW);
            }

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (resultCode == RESULT_OK) {
                permissionOverdrawGranted = true;
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
