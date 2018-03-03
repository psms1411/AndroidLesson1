package com.py.ps.androidlesson1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EdgeDetectionActivity extends AppCompatActivity implements Runnable{

    int noOfPressedTimes = 0;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            setContentView(R.layout.activity_background_timer);
            Toast.makeText(getApplicationContext(), "Message reecived", Toast.LENGTH_LONG).show();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edge_detection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final TextView tview =  findViewById(R.id.edgeTextView);

        Thread newThread = new Thread(EdgeDetectionActivity.this);
        newThread.start();

        Button detectButton = findViewById(R.id.detectButton);
        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOfPressedTimes++;
                tview.setText("Pressed Button "+noOfPressedTimes +" times\nAnd computation loop at "+ "("+xi+","+yi+") pixles");
            }
        });

    }


    @Override
    public void run() {
        edgeDetect();
    }

    int xi, yi;
    private double edgeDetect() {
        int x_pixles = 4000;
        int y_pixles = 3000;

        double image_transform = 0;
        for(xi=0; xi<x_pixles; xi++) {
            for(yi=0; yi<y_pixles; yi++) {
                image_transform = Math.cosh(xi*yi/x_pixles/y_pixles);
            }
        }

        mHandler.sendEmptyMessage(0);
        return image_transform;
    }
}
