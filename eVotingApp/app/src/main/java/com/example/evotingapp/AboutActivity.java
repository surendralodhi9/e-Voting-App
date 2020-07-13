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
        aboutHeading=(TextView)findViewById(R.id.aboutHeading);

        aboutHeading.setText("AboutApplication");


        about.setMovementMethod(new ScrollingMovementMethod());
        about.setText("Hi, there!");

    }
}