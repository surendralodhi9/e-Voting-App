package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private TextView contactInfo;
    private  String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        message="Mobile: +918719051672\nEmail: surendralodhi9@gmail.com\n" +
                "            lodhisurendra9@gmail.com";
        contactInfo=(TextView)findViewById(R.id.contactView);
        contactInfo.setText(message);
    }
}