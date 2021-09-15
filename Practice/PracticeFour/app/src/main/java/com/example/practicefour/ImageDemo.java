package com.example.practicefour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ImageDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_demo);
        //ImageView iv = findViewById(R.id.imageView2);
        //iv.setImageResource(R.drawable.img2);

        InputStream inputStream = getResources().openRawResource(R.raw.random);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String text = "";
        try {
            String line = br.readLine();
            while(line!=null){
                text+=line;
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView txtRandom = findViewById(R.id.textView);
        txtRandom.setText(text);
    }
}