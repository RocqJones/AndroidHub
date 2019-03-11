package com.rocqjones.intoverflown.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {

     SeekBar tsBar;
     TextView ttView;
     Button controlBtn;
     CountDownTimer countDownTimer;
     Boolean counterIsActive = false;

     public void updateTimer(int secondsLeft) {

         int minutes = (int) secondsLeft/60;
         int seconds = secondsLeft - minutes * 60;

         String secondString = Integer.toString(seconds);
         if (seconds <= 9) { //adding 0 before whole number
             secondString = "0" + secondString;
         }

         //updating timer
         ttView.setText(Integer.toString(minutes) + ":" + secondString);
     }

     //when it get to 0.00 you don't have to click stop
     public void resetTimer() {
         ttView.setText("0.30");
         tsBar.setProgress(30); //30 seconds
         countDownTimer.cancel();
         controlBtn.setText("GO!");
         tsBar.setEnabled(true);
         counterIsActive = false;
     }

     //controlTimer method
     public void controlTimer(View view) {

         if (counterIsActive == false) {

             counterIsActive = true;
             //then disable seekBar
             tsBar.setEnabled(false);
             controlBtn.setText("STOP!");

             countDownTimer = new CountDownTimer(tsBar.getProgress() * 1000 + 100, 1000) {

                 @Override
                 public void onTick(long millisUntilFinished) {
                     updateTimer((int) millisUntilFinished/1000);
                 }

                 @Override
                 public void onFinish() {
                     //updating timer txt view to '0'
                     //ttView.setText("0.00");
                     //Log.i("finished", "timer done");
                     //we change log to play log instead
                     //call reset timer and replace it with ttView.setText()
                     resetTimer();
                     MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airplanelanding);
                     mplayer.start();
                 }
             }.start();
         } else {
             //what will happen when counter is true. It's we reset the timer
             resetTimer();
         }

     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start - SeekBar and TextView
        tsBar = (SeekBar) findViewById(R.id.timerSeekBar);
        ttView = (TextView) findViewById(R.id.timerTextView); //final means we can not change
        controlBtn = (Button) findViewById(R.id.controllerBtn);

        tsBar.setMax(600);
        tsBar.setProgress(30);

        tsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
