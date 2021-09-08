package com.example.practicetwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main", "Hello Logcat");
        System.out.println("Sysout");

        Toast.makeText(this, "Hello Me", Toast.LENGTH_LONG).show();
    }
}