package com.example.quicktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String LENGTH = "com.example.quicktimer.LENGTH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTimer5(View view){
        Intent intent = new Intent(this, TimerActivity.class);
        int length = 5; //in minutes
        intent.putExtra(LENGTH, length);
        startActivity(intent);
    }
}
