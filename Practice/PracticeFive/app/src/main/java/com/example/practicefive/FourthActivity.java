package com.example.practicefive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class FourthActivity extends AppCompatActivity {
    TextView txt1;
    TextView txt2;
    TextView txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        txt1 = findViewById(R.id.txtOne);
        txt2 = findViewById(R.id.txtTwo);
        txt3 = findViewById(R.id.txtThree);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        txt1.setText(b.getString("name"));
        txt2.setText(b.getInt("age")+"");
        txt3.setText(Arrays.toString(b.getStringArray("grades")));
    }
}