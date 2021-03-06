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
    private Button registerVoterButton;
    private Button modifyVoterButton;
    public static String Adminusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        setUpAllUi();
        Intent intent=getIntent();
        String Username=intent.getStringExtra("Username");
        Adminusername=Username;
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
                intent.putExtra("adminusername",welcomeUsername.getText().toString());

                startActivity(intent);
                //System.out.println("hhhhhhhhhhhhhhhhh");
            }
        });
        registerVoterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterVoterActivity.class);
                startActivity(intent);
            }
        });
        modifyVoterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1=new Intent(getApplicationContext(),displayVoterActivity.class);
                intent1.putExtra("adminusername",welcomeUsername.getText().toString());
                startActivity(intent1);
            }
        });
    }
    protected void setUpAllUi(){


        welcomeUsername=(TextView)findViewById(R.id.welcomeUsername);
        registerButton=(Button)findViewById(R.id.registerPageButton);
        modifyButton=(Button)findViewById(R.id.modifyPageButton);
        modifyVoterButton=(Button)findViewById(R.id.modifyVoterButton);
        registerVoterButton=(Button)findViewById(R.id.registerVoterButton);
    }
}