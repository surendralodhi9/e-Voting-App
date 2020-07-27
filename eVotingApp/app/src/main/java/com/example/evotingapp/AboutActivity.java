package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    protected TextView about;
    protected TextView aboutHeading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about=(TextView)findViewById(R.id.aboutTextView);


        String message="* E-voting App is application for remote voting.\n" +
                "\n* You can vote from anywhere in the world.\n" +
                "\n * This App helps to increase voting percentage and saving the time of voters.";
        about.setMovementMethod(new ScrollingMovementMethod());
        about.setText(message);

    }
}