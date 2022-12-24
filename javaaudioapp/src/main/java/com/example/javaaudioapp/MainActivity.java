package com.example.javaaudioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  {

    // MEDIA PLAYER CLASS
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void play (View view) {
        mediaPlayer.start();
    }

    public void pause (View view) {
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mediaPlayer  = MediaPlayer.create( this, R.raw.alcohol2);
        SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeSeekBar);

        // VOLUME CONTROL SLIDER
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i( "Seekbar Changed", Integer.toString(i));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // SEEKBAR CONTROL SLIDER
        SeekBar scrubSeekBar = (SeekBar) findViewById(R.id.scrubSeekBar);
        scrubSeekBar.setMax(mediaPlayer.getDuration());
        scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i( "Scrub Seekbar Moved", Integer.toString(i));

                // PLAYBACK WITH SEEKBAR-PLAYER-TIMER
                mediaPlayer.seekTo(i);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // TIMER SCHEDULER
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());

            }
        },0,1000);
    }
}