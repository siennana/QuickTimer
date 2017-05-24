package com.crispycollision.quicktimer;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String LENGTH = "com.crispycollision.quicktimer.LENGTH";

    // the notification manager for this app
    public static NotificationManager mNotifyMgr;

    // manages the vibrator
    public static Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // cancel all notifications
        if(mNotifyMgr != null) {
            mNotifyMgr.cancelAll();
        }

        // cancel any ongoing vibration
        if(vibrator != null){
            vibrator.cancel();
        }
    }

    public void startTimer5(View view){
        Intent intent = new Intent(this, TimerActivity.class);
        int length = 5;
        intent.putExtra(LENGTH, length);
        startActivity(intent);
    }

    public void startTimer10(View view){
        Intent intent = new Intent(this, TimerActivity.class);
        int length = 10;
        intent.putExtra(LENGTH, length);
        startActivity(intent);
    }

    public void startTimer30(View view){
        Intent intent = new Intent(this, TimerActivity.class);
        int length = 30;
        intent.putExtra(LENGTH, length);
        startActivity(intent);
    }

    public void startTimer45(View view){
        Intent intent = new Intent(this, TimerActivity.class);
        int length = 45;
        intent.putExtra(LENGTH, length);
        startActivity(intent);
    }

    public void startTimer60(View view){
        Intent intent = new Intent(this, TimerActivity.class);
        int length = 60;
        intent.putExtra(LENGTH, length);
        startActivity(intent);
    }
}
