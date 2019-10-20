package com.example.cantpickup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //https://developer.android.com/about/versions/oreo/background.html#broadcasts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // starts the background service so that incoming calls are triggered when the app is closed
        startService(new Intent(this, CallService.class));

        //alerts calls
        Intent permissionsIntent = new Intent(this, PermissionsActivity.class);
        startActivity(permissionsIntent);
    }

}
