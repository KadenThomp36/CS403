package com.example.inputcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ListView lstSumm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lstSumm = findViewById(R.id.lstSumm);

        Intent intent = getIntent();

        ArrayList<String> summary = intent.getStringArrayListExtra("summary");

        ArrayAdapter<String> summ = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, summary);
        lstSumm.setAdapter(summ);
    }
}