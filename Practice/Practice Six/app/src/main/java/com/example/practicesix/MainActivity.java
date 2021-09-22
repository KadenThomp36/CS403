package com.example.practicesix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int ctr = 0;
    TextView txtCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Lifecycle", "onCreate() was called");

        txtCount = findViewById(R.id.txtCount);
    }

    public void increment(View view){
        ctr++;
        txtCount.setText(ctr+"");
    }

    public void decrement(View view){
        ctr--;
        txtCount.setText(ctr+"");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart() was called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop() was called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy() was called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause() was called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume() was called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle", "onRestart() was called");
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        super.applyOverrideConfiguration(overrideConfiguration);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("ctr", ctr);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        ctr = savedInstanceState.getInt("ctr");
        txtCount.setText(ctr+"");
        super.onRestoreInstanceState(savedInstanceState);

        //this can all be done in onStart as well !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public void reset(View view){
        ctr = 0;
        txtCount.setText(ctr+"");
        //finish();
    }
}