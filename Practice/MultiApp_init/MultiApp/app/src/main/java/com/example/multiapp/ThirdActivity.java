package com.example.multiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView txtShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        txtShow = findViewById(R.id.txtShow);

        Intent intent = getIntent();
        String x = intent.getStringExtra("msg");
        txtShow.setText(x);

    }
    public void goBack(View v){

    }
}