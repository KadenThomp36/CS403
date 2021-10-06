package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    SeekBar seekNumOfCommands;
    TextView txtNumOfCommands;
    CheckBox chkTap;
    CheckBox chkDoubleTap;
    CheckBox chkSwipe;
    CheckBox chkZoom;
    Bundle b;
    int seekSetting = 10;
    int seekMax = 30;
    int seekMin = 5;
    int amountSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        b = this.getIntent().getExtras();

        seekNumOfCommands = findViewById(R.id.seekNumOfCommands);
        txtNumOfCommands = findViewById(R.id.txtNumOfCommands);
        chkTap = findViewById(R.id.chkTap);
        chkDoubleTap = findViewById(R.id.chkDoubleTap);
        chkSwipe = findViewById(R.id.chkSwipe);
        chkZoom = findViewById(R.id.chkZoom);

        seekNumOfCommands.setMax(seekMax - seekMin);

        readBundle();



        seekNumOfCommands.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                seekSetting = seekMin + value;
                txtNumOfCommands.setText(seekSetting+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void Save(View view){
        Bundle b = createBundle();
        if (amountSelected < 2){
            Toast.makeText(this, "Select at least two commands, you've selected " + amountSelected, Toast.LENGTH_SHORT).show();
            amountSelected = 0;
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void Cancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void readBundle(){
        String Commands = b.getString("Commands");
        seekSetting = b.getInt("numCommands");
        seekNumOfCommands.setProgress(seekSetting - seekMin);
        txtNumOfCommands.setText(seekSetting+"");

        if (Commands.contains("1")){
            chkTap.setChecked(true);
        }else
            chkTap.setChecked(false);

        if (Commands.contains("2")){
            chkDoubleTap.setChecked(true);
        }else
            chkDoubleTap.setChecked(false);

        if (Commands.contains("3")){
            chkSwipe.setChecked(true);
        }else
            chkSwipe.setChecked(false);

        if (Commands.contains("4")){
            chkZoom.setChecked(true);
        }else
            chkZoom.setChecked(false);
    }

    public Bundle createBundle(){
        Bundle b = new Bundle();
        String Commands = "";

        if (chkTap.isChecked()){
            Commands += "1";
            amountSelected++;
        }
        if (chkDoubleTap.isChecked()){
            Commands += "2";
            amountSelected++;
        }
        if (chkSwipe.isChecked()){
            Commands += "3";
            amountSelected++;
        }
        if (chkZoom.isChecked()){
            Commands += "4";
            amountSelected++;
        }
        b.putInt("numCommands", seekSetting);
        b.putString("Commands", Commands);

        return b;
    }
}