package com.example.practicetwelve;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    Sensor sensor_acc;
    Sensor sensor_gravity;
    Sensor sensor_gyro;
    Sensor sensor_stepdetect;
    Sensor sensor_stepCount;
    Sensor sensor_light;

    ConstraintLayout root;

    float maxLight;
    int ctr = 0;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.root);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) ==
                PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new
                    String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_gravity = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor_gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor_stepdetect = sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensor_stepCount = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensor_light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d("Acc", Arrays.toString(event.values));
        }

        if(event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            Log.d("Acc", Arrays.toString(event.values));
        }

        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            Log.d("Acc", Arrays.toString(event.values));
        }

        if(event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            Log.d("Acc", Arrays.toString(event.values));
        }

        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            Log.d("Acc", Arrays.toString(event.values));
        }

        if(event.sensor.getType() == Sensor.TYPE_LIGHT){

            int light =(int) (((event.values[0])/(maxLight)) *(255));
            Log.d("light", light+" "+maxLight);
            root.setBackgroundColor(Color.rgb(light,light,light));
            if (ctr == 0){
                maxLight = event.values[0];
                ctr++;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStart(){
        super.onStart();
        sm.registerListener(this, sensor_acc, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_gravity, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_gyro, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_stepdetect, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_stepCount, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensor_light, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onStop(){
        super.onStop();
        sm.unregisterListener(this);


    }
}