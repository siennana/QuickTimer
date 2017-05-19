package com.example.quicktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.quicktimer.R.id.textViewTimer;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // get the intent that started this
        Intent intent = getIntent();
        int length = intent.getIntExtra(MainActivity.LENGTH, 0);

        // get the timer textview, set what it should say
        TextView textView = (TextView) findViewById(textViewTimer);
        textView.setText("Length is " + length + ".");
    }
}
