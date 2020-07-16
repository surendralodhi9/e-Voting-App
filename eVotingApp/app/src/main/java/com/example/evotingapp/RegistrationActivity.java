package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {


    private EditText Username;
    private EditText Name;
    private EditText FatherName;
    private EditText Constituency;
    private EditText Sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}