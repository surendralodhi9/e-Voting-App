package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminHomeActivity extends AppCompatActivity {

    private TextView welcomeUsername;
    private Button registerButton;
    private Button modifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        setUpAllUi();
        Intent intent=getIntent();
        String Username=intent.getStringExtra("Username");
        welcomeUsername.setText(Username);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });

        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),ModifyCandidateActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void setUpAllUi(){


        welcomeUsername=(TextView)findViewById(R.id.welcomeUsername);
        registerButton=(Button)findViewById(R.id.registerPageButton);
        modifyButton=(Button)findViewById(R.id.modifyPageButton);
    }
}