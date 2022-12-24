package com.example.alertdialogue_java;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // DECLARED SHARED-PREFERENCE
    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.english:
                setLanguage("English");
                return true;
            case R.id.spanish:
                setLanguage("Spanish");
                return true;
            default:
                return false;
        }
    }

    public void setLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();
        textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences
                ("com.example.alertdialogue_java", Context.MODE_PRIVATE);

        String language = sharedPreferences.getString("Language", "Error");
        Log.i("THE LANG",language);
        if (language.equals("Error")) {

            // ALERT SETTINGS
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are You Sure!?")
                    .setMessage("Do You Definitely Want To Do This?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // SET ENGLISH
                            setLanguage("ENGLISH");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // SET SPANISH
                            setLanguage("SPANISH");

                        }
                    })
                    .show();
        } else
        {
            textView.setText(language);
        }
    }
}