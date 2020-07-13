package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    public ImageButton imageButtonAdmin;
    public ImageButton imageButtonLogin;
    public ImageButton imageButtonAbout;
    public ImageButton imageButtonHelp;
    public ImageButton imageButtonContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpAllUi();

        imageButtonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(intent);
            }
        });

        imageButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        imageButtonAbout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(intent);
            }

        });
        imageButtonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),ContactActivity.class);
                startActivity(intent);
            }
        });

        imageButtonHelp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(getApplicationContext(),HelpActivity.class);
                startActivity(intent);
            }

        });
    }

    protected void setUpAllUi()
    {

        imageButtonAdmin=(ImageButton)findViewById(R.id.imageButtonAdmin);
        imageButtonLogin=(ImageButton)findViewById(R.id.imageButtonLogin);
        imageButtonAbout=(ImageButton)findViewById(R.id.imageButtonAbout);
        imageButtonContact=(ImageButton)findViewById(R.id.imageButtonContact);
        imageButtonHelp=(ImageButton)findViewById(R.id.imageButtonHelp);

    }
}