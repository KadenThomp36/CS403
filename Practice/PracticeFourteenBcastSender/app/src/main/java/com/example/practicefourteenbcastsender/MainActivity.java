package com.example.practicefourteenbcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    private static final String ACTION_INTER_APP = "com.example.ACTION_INTER_APP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
    }

    public void sendBcast (View v){
        String userInput = editText.getText()+"";
        Intent i = new Intent(ACTION_INTER_APP);
        i.putExtra("msg", userInput);

        sendBroadcast(i);
    }
}