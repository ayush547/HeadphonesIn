package com.example.headphonesin;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.headphonesin.App.CHANNEL_ID;

public class MyService extends Service {

    Boolean pluggedIn,old;

    private void timer(){
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                fn();
            }
        }, 1000,1000);
    }

    private void fn() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        pluggedIn = audioManager.isWiredHeadsetOn();
        if(pluggedIn!=old) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Audio Service")
                    .setContentText(pluggedIn ? "Headsets Plugged In" : "Headsets Not Plugged")
                    .setSmallIcon(R.drawable.clip)
                    .setContentIntent(pendingIntent)
                    .build();
            startForeground(1, notification);
        }
        old=pluggedIn;
    }
    AudioManager audioManager;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
