package com.py.ps.androidlesson1;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BackgroundTimerActivity extends AppCompatActivity {

    private long mStartTime = 0L;
    TextView pressLabel, timeLabel;
    private int pressedTimes = 0;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_timer);

        if(mStartTime == 0L) {
            mStartTime = SystemClock.uptimeMillis();
            handler.removeCallbacks(mUpdateTask);
            //handler.postDelayed(mUpdateTask, 200);
        }

        /**
         * CountDown Timer
         */
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millsUntilFinished) {
                timeLabel.setText("seconds remaining:"+millsUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeLabel.setText("done!"); //Done sound- Add later
                MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tone2);
                mPlayer.start();

            }
        }.start();

        timeLabel = findViewById(R.id.timerView);
        pressLabel = findViewById(R.id.timeButton);

        Button timeButton = findViewById(R.id.timeButton);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedTimes++;
                pressLabel.setText("Pressed "+pressedTimes+ " times");
            }
        });

    }

private Runnable mUpdateTask = new Runnable() {
    @Override
    public void run() {
        final long start = mStartTime;
        long millis = SystemClock.uptimeMillis() - start;
        int seconds = (int)(millis/1000);
        int mins = seconds/60;
        seconds = seconds%60;
        timeLabel.setText(mins+":"+String.format("%02d", seconds));
        //timeLabel.setText(mins+":"+seconds);

        handler.postDelayed(mUpdateTask, 2000);
    }
};

    @Override
    protected void onPause() {
        //handler.removeCallbacks(mUpdateTask);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //handler.postDelayed(mUpdateTask, 200);
    }
}
