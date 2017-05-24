package com.crispycollision.quicktimer;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import static com.crispycollision.quicktimer.R.id.textViewTimer;

public class TimerActivity extends AppCompatActivity {
    // keeps track of whether countdown successfully completed
    // for notification purposes
    boolean completedCountdown = false;

    // timer and vibrator for managing the activity
    CountDownTimer countDownTimer;

    // the notification
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // get the intent that started this
        Intent intent = getIntent();
        int minutes = intent.getIntExtra(MainActivity.LENGTH, 0);
        long seconds = (long) minutes * 60;
        long milliseconds = seconds * 1000;

        // create the vibrator
        MainActivity.vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // get the timer textview, set what it should say
        final TextView textView = (TextView) findViewById(textViewTimer);

        // create the notification manager and its notification
        MainActivity.mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("QuickTimer");
        // At later point, set content text

        mBuilder.setContentIntent(resultPendingIntent);

        // create the countdown timer
        countDownTimer = new CountDownTimer(milliseconds, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(formatTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                completedCountdown = true;

                textView.setText("Countdown over!!!");

                // vibration will start with no delay, go for 500 milliseconds, pause for 800
                // milliseconds, and so on for five times
                long [] vibratePattern = {0, 500, 800, 500, 800, 500, 800, 500, 800, 500};
                MainActivity.vibrator.vibrate(vibratePattern, -1);

                // notify user that countdown is over
                int mNotificationId = 001;
                mBuilder.setContentText("Countdown finished!!");
                MainActivity.mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        };
        countDownTimer.start();

    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        // cancel any notification that the countdown has finished
        MainActivity.mNotifyMgr.cancel(001);

        // cancel the countdown timer
        countDownTimer.cancel();

        // stop the vibration if it is happening
        MainActivity.vibrator.cancel();

        if(!completedCountdown){
            // notify user that countdown has been canceled
            Context context = getApplicationContext();
            CharSequence text = "Countdown canceled";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    protected String formatTime(long milliseconds){
        long totalSeconds = milliseconds / 1000;
        int dispMinutes = (int) totalSeconds / 60;
        int dispSeconds = (int) totalSeconds % 60;
        return String.format("%d:%02d", dispMinutes, dispSeconds);
    }
}
