package com.example.practicefive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = findViewById(R.id.txtInput);
    }

    public void startSecond(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void startThird(View view){
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("msg", txtMessage.getText().toString());
        startActivity(intent);
    }
    public void startFourth(View view){
        Intent intent = new Intent(this, FourthActivity.class);
        //intent.putExtra("msg", txtMessage.getText().toString());
        Bundle b = new Bundle();
        b.putString("name", "John");
        b.putInt("age", 22);
        b.putStringArray("grades", new String[]{"A","B","C"});
        intent.putExtras(b);
        startActivity(intent);
    }

}