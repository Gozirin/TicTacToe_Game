package com.example.javaanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // BOOLEAN TO TRACK WHICH ANIM IS SHOWING
    boolean bartIsShowing = true;

    public void fade (View view) {
        Log.i("Info","Imageview tapped");

        ImageView bartViewAnim = (ImageView) findViewById(R.id.bartViewAnim1);
        ImageView homerViewAnim = (ImageView) findViewById(R.id.homerViewAnim2);
        //ImageView catViewAnim = (ImageView) findViewById(R.id.catViewAnim3);


        // (1.) FIRST SPLASH ANIMATIONS, WITH BOOLEAN TRACKER
        if (bartIsShowing) {
            bartIsShowing = false;
            bartViewAnim.animate().alpha(1).setDuration(2000);
            homerViewAnim.animate().alpha(0).setDuration(2000);
            // catViewAnim.animate().alpha(2).setDuration(2000);
        }

        else
        {
            bartIsShowing = true;
            bartViewAnim.animate().alpha(0).setDuration(2000);
            homerViewAnim.animate().alpha(1).setDuration(2000);
            //catViewAnim.animate().alpha(2).setDuration(2000);
        }


        //  (2.) OTHER FORMS ANIMATIONS & COMMENT BOOLEAN;
//         bartViewAnim.animate().translationXBy(-1000).setDuration(2000);
//         homerViewAnim.animate().translationXBy(-1000).setDuration(2000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}