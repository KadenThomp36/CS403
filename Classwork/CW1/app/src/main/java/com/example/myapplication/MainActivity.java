package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void convertTemp(View view){
        TextView txtInput = findViewById(R.id.txtInput);

        double output;
        try {
            double input = Double.parseDouble(txtInput.getText().toString());
            output = (input * 9/5) + 32;
        } catch (Exception e){
            output = 0;
        }


        Toast.makeText(this, Double.toString(output), Toast.LENGTH_SHORT).show();
    }

    public void clear(View view){
        TextView txtInput = findViewById(R.id.txtInput);
        txtInput.setText("");
    }
}