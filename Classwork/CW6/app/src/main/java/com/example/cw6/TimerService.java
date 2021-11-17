package com.example.cw6;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class TimerService extends Service {

    public final String CHANNEL_ID = "Notification";
    boolean stopTimer = false;
    int time;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();

    }

    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Notification notification = buildNotification("Timer", "");
        startForeground(1, notification);

        if (intent.getAction().equals("START_TIMER")) {
            stopTimer = false;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    time = intent.getIntExtra("time", 10);

                    for (int i = time; i >= 0; i--){
                        try {
                            if (stopTimer)
                                break;
                            Thread.sleep(1000);
                            Notification timerNotification = buildNotification("Timer", "00:"+i);
                            Intent j = new Intent("UPDATE");
                            j.putExtra("time",i+"");
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(j);
                            startForeground(1, timerNotification);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }

                });
            t.start();
            } else if (intent.getAction().equals("STOP_TIMER")) {
                stopTimer = true;
                stopForeground(true);
                stopSelf();
            }




        return START_STICKY;
    }

    public Notification buildNotification(String title, String content) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        return notification;
    }

    public void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Location Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
    }
}