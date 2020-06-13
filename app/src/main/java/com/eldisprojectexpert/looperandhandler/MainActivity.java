package com.eldisprojectexpert.looperandhandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean isStopLoop;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view){
        isStopLoop = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isStopLoop){
                    Log.d(TAG, "Thread id " + Thread.currentThread().getId() + " startThread()");
                }
            }
        }).start();

    }

    public void stopThread(View view){
        isStopLoop = false;
    }
}