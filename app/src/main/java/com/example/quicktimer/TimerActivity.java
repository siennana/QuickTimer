package com.example.quicktimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static com.example.quicktimer.R.id.textViewTimer;

public class TimerActivity extends AppCompatActivity {

    // timer and vibrator for managing the activity
    CountDownTimer countDownTimer;
    Vibrator vibrator;

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
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // get the timer textview, set what it should say
        final TextView textView = (TextView) findViewById(textViewTimer);

        // create the countdown timer
        countDownTimer = new CountDownTimer(milliseconds, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(formatTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                textView.setText("Countdown over!!!");

                // vibration will start with no delay, go for 500 milliseconds, pause for 800
                // milliseconds, and so on for five times
                long [] vibratePattern = {0, 500, 800, 500, 800, 500, 800, 500, 800, 500};
                vibrator.vibrate(vibratePattern, -1);
            }
        };
        countDownTimer.start();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        // cancel the countdown timer
        countDownTimer.cancel();

        // stop the vibration if it is happening
        vibrator.cancel();
    }

    protected String formatTime(long milliseconds){
        long totalSeconds = milliseconds / 1000;
        int dispMinutes = (int) totalSeconds / 60;
        int dispSeconds = (int) totalSeconds % 60;
        return String.format("%d:%02d", dispMinutes, dispSeconds);
    }
}
