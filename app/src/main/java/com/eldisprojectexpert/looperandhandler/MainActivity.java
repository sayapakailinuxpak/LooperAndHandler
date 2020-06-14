package com.eldisprojectexpert.looperandhandler;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    boolean isStopLoop;
    private static final String TAG = "MainActivity";
    Button buttonStartThread, buttonStopThread;
    TextView textViewNumber;
    int count = 0;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStartThread = findViewById(R.id.button_start_thread);
        buttonStopThread = findViewById(R.id.button_stop_thread);
        textViewNumber = findViewById(R.id.textview_number);

        buttonStartThread.setOnClickListener(this);
        buttonStopThread.setOnClickListener(this);

        Log.i(TAG, "onCreate: at Thread id : " + Thread.currentThread().getId());

        //initialize Handler with reference to Looper
        handler = new Handler(getApplicationContext().getMainLooper()); //should have reference to message queue of the UI Thread.
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start_thread :
                isStopLoop = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isStopLoop){
                            try {
                                Thread.sleep(1000);
                                count++;
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            Log.i(TAG, "Thread id " + Thread.currentThread().getId() + " startThread() and count = " + count);
                            //call UI element from separate Thread
//                            textViewNumber.setText("" + count);
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    textViewNumber.setText("" + count);
//                                }
//                            });

                            //the simpler version
                            textViewNumber.post(new Runnable() {
                                @Override
                                public void run() {
                                    textViewNumber.setText("" + count);
                                }
                            });

                        }

                    }
                }).start();
                break;
            case R.id.button_stop_thread :
                isStopLoop = false;
                break;
            default: break;
        }
    }



}