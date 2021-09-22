package com.example.multiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(this,"Activity started", Toast.LENGTH_LONG).show();

    }

    public void goBack(View v){
        Intent it = new Intent();
        it.putExtra("msg", "Activity 2 is done!");
        setResult(222,it);
        finish();
    }
}