package com.example.multiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.gson.Gson;

import java.util.Arrays;

public class FourthActivity extends AppCompatActivity {

    TextView txt1;
    TextView txt2;
    TextView txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);


        Intent intent = getIntent();

//        Bundle b = intent.getExtras();
//        txt1.setText(b.getString("msg"));
//        txt2.setText(b.getInt("num")+"");
//        txt3.setText(Arrays.toString(b.getStringArray("list")));

//        Student s = (Student) intent.getSerializableExtra("john");
//        txt1.setText(s.name);
//        txt2.setText(s.age+"");
//        txt3.setText(s.gpa+"");

        Student x = new Gson().fromJson(intent.getStringExtra("json_str"),Student.class);
        txt1.setText(x.name);
        txt2.setText(x.age+"");
        txt3.setText(x.gpa+"");


    }

    public void goBack(View v){

    }
}