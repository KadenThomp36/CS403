package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;

    int numCommands = 10;
    String Commands = "1 2 3 4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("com.example.settings", Context.MODE_PRIVATE);

        numCommands = sharedPref.getInt("numCommands", 9);
        Commands = sharedPref.getString("Commands", "1234");

        Bundle b;
        b = this.getIntent().getExtras();
        if (!(b == null)){
            numCommands = b.getInt("numCommands");
            Commands = b.getString("Commands");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("numCommands", numCommands);
            editor.putString("Commands", Commands);
            editor.commit();
        }

    }

    public void openSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        Bundle b = new Bundle();
        b.putInt("numCommands", numCommands);
        b.putString("Commands", Commands);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void NewGame(View view){
        Intent intent = new Intent(this, MainGame.class);
        Bundle b = new Bundle();
        b.putInt("numCommands", numCommands);
        b.putString("Commands", Commands);
        intent.putExtras(b);
        startActivity(intent);
    }
}