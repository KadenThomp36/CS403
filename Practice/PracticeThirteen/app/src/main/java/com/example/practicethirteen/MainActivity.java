package com.example.practicethirteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int REQUEST_LOCATION_PERMISSION = 1;
    Geocoder geocoder;
    FusedLocationProviderClient locationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geocoder = new Geocoder(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("locdemo", "getLocation: permissions not granted");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            Log.d("locdemo", "getLocation: permissions granted");
        }

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        locationProviderClient.getLastLocation()
//                .addOnSuccessListener(location -> Log.d("locdemo", location.toString()))
//                .addOnFailureListener(e -> Log.d("locdemo", "Could not fetch location"));

        locationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d("loc1", location.toString());
                        try {
                            List<Address> addresses =  geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                            Log.d("Loc1", addresses.get(0).getAddressLine(0).toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }
    public void getUpdates(View v){
        LocationRequest locationRequest = getLocationRequest();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_LOCATION_PERMISSION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("locdemo", "getLocation: permissions now granted");
            }
            else{
                Log.d("locdemo", "getLocation: permissions not granted");
            }
        }
    }
}