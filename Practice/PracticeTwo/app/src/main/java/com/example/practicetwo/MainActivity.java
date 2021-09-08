package com.example.practicetwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnToast;
    Button btnCount;
    TextView txtCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCount = findViewById(R.id.btnCount);
        btnToast = findViewById(R.id.btnToast);
        txtCounter = findViewById(R.id.txtCounter);


    }

    public void showToast(View v){
        Toast.makeText(this, txtCounter.getText().toString(), Toast.LENGTH_LONG).show();
    }
    public void incrementCount(View v){
        int count = Integer.parseInt(txtCounter.getText().toString());
        count++;
        txtCounter.setText(Integer.toString(count));
    }
}