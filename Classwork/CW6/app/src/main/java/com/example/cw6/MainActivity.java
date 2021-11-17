package com.example.cw6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int time;
    int time2;
    IntentFilter intentFilter;
    TextView txtTime,txtTitle;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        txtTime = findViewById(R.id.txtTime);
        txtTitle = findViewById(R.id.txtTitle);

        btnStop.setEnabled(false);
        intentFilter = new IntentFilter("UPDATE");

        //compute steps and distance and set it to the textviews
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                time2 = Integer.parseInt(intent.getStringExtra("time"));
                txtTitle.setText("00:"+time2);
                if (time2 == 0){
                    btnStart.setEnabled(true);
                    btnStop.setEnabled(false);
                }

            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    public void startTimer(View view){
        Intent i = new Intent(getApplicationContext(), TimerService.class);
        i.setAction("START_TIMER");
        time = Integer.parseInt(txtTime.getText().toString());
        i.putExtra("time", time);
        startForegroundService(i);
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }

    public void stopTimer(View view){
        Intent i = new Intent(getApplicationContext(), TimerService.class);
        i.setAction("STOP_TIMER");
        startForegroundService(i);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }
}