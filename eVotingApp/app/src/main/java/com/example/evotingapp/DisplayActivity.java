package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {


    private TextView displayUsername;
    private TextView displayName;
    private TextView displayFatherName;
    private TextView displayConstituency;
    private TextView displaySign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        setUpAllUi();

        Intent intent=getIntent();
        Candidate candidate=(Candidate)intent.getSerializableExtra("candidate");
        displayUsername.setText(candidate.Username);
        displayName.setText(candidate.Name);
        displayFatherName.setText(candidate.FatherName);
        displayConstituency.setText(candidate.Constituency);
        displaySign.setText(candidate.Sign);
    }
    protected void setUpAllUi()
    {

        displayUsername=(TextView)findViewById(R.id.displayEmail);
        displayName=(TextView)findViewById(R.id.displayName);
        displayFatherName=(TextView)findViewById(R.id.displayFatherName);
        displayConstituency=(TextView)findViewById(R.id.displayConstituency);
        displaySign=(TextView)findViewById(R.id.displaySign);
    }
}