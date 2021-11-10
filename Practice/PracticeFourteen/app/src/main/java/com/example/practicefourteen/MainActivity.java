package com.example.practicefourteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import com.example.practicefourteen.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_INTER_APP = "com.example.ACTION_INTER_APP";
    SystemBroadcastReceiver sReceiver;
    TextView txtUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sReceiver = new SystemBroadcastReceiver();
        txtUpdate = findViewById(R.id.txtUpdate);

        IntentFilter airplaneFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        IntentFilter smsFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        IntentFilter interFilter = new IntentFilter(ACTION_INTER_APP);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 0);
        } else {
            // register sms receiver
            this.registerReceiver(sReceiver, smsFilter);
        }

        this.registerReceiver(sReceiver,airplaneFilter);
        this.registerReceiver(sReceiver,smsFilter);
        this.registerReceiver(sReceiver, interFilter);

    }

    class SystemBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();
            Log.d("bcast", "action is " + intentAction);
            switch (intentAction) {
                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    Log.d("bcast", "Ariplane mode toggled");
                    Log.d("bcast", intent.getBooleanExtra("state", false) + "");

                    break;
                case Telephony.Sms.Intents.SMS_RECEIVED_ACTION:
                    Log.d("bcast", "SMS received");
                    Bundle b = intent.getExtras();
                    String msg = "";
                    String num = "";
                    if (b != null) {
                        String format = b.getString("format");
                        Object[] temp = (Object[]) b.get("pdus");
                        for (int i = 0; i < temp.length; i++) {
                            SmsMessage sms = SmsMessage.createFromPdu((byte[]) temp[i], format);
                            msg += sms.getMessageBody();
                            num = sms.getOriginatingAddress();

                        }
                    }
                    Log.d("bcast", "Message from " + num);
                    Log.d("bcast", msg);
                    break;
                case ACTION_INTER_APP:
                    Log.d("bcast", "Received broadcast from second app");
                    String msgg = intent.getStringExtra("msg");
                    txtUpdate.setText(msgg);

            }
        }


    }
}

