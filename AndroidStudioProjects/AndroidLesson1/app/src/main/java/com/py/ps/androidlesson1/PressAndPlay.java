package com.py.ps.androidlesson1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PressAndPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_and_play);
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

        Button pressAndPlay = (Button) findViewById(R.id.trigger);
        pressAndPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Create Task to play the music
                Thread initBkgdThrs = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        play_music();
                    }
                });

                initBkgdThrs.start();
            }
        });
    }


    int[] notes = {R.raw.tone2, R.raw.a4};
    int NOTES_DURATION=40; //milisec
    MediaPlayer mediaPlayer;

    private void play_music() {
        for(int i=0; i<2; i++) {
            if(!paused) {
                if(mediaPlayer!=null) {mediaPlayer.release();}
                mediaPlayer = MediaPlayer.create(this, notes[i%2]);
                mediaPlayer.start();

//                try{
//                    //Thread.sleep(NOTES_DURATION);
//                }
//                catch (InterruptedException exp){
//                    exp.printStackTrace();
//                }
            }
        }
    }

    boolean paused = false;

    @Override
    protected void onPause() {
        paused = true;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        paused=false;
    }
}
