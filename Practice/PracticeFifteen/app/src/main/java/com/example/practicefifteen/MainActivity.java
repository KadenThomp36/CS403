package com.example.practicefifteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    Switch tracker;
    public final int REQUEST_LOCATION_CODE = 1;
    public String TAG = "loc";
    BroadcastReceiver br;
    IntentFilter locFilter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locFilter = new IntentFilter("LOCATION_UPDATE");
        tv = findViewById(R.id.textView);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("LOCATION_UPDATE")){
                    tv.setText(intent.getStringExtra("loc"));
                }
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(br, locFilter);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
        } else {
            Log.d("Loc_demo", "permissions already granted");
        }
        tracker = findViewById(R.id.switch1);

        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch s = (Switch) v;
                if (s.isChecked()) {
                    //start updates
                    Intent i = new Intent(getApplicationContext(), LocationUpdate.class);
                    i.setAction("START_TRACKING");
                    startForegroundService(i);
                } else {
                    //stop updates
                    Intent i = new Intent(getApplicationContext(), LocationUpdate.class);
                    i.setAction("STOP_TRACKING");
                    Log.d(TAG, "stopping.....................");
                    startForegroundService(i);
                }
            }
        });

    }
}