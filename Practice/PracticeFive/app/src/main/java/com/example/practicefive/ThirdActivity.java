package com.example.practicefive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView txtShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        txtShow = findViewById(R.id.txtShow);

        Intent intent = getIntent();
        System.out.println(intent.getStringExtra("msg"));
        txtShow.setText(intent.getStringExtra("msg"));
    }
}