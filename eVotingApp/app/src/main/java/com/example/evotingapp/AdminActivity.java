package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {

    protected Button loginButon;
    DatabaseReference databaseReference;
    protected EditText editEmail;
    protected EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        setUpAllUi();

        loginButon.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                dataBaseOperations();
            }
        });

    }
    public void dataBaseOperations()
    {

        try {

            databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");
            int Id=1;
            String Username=editEmail.getText().toString().trim();
            String Password=editPassword.getText().toString().trim();

            Admin admin=new Admin(Id,Username,Password);
            System.out.println(admin.Password);

            databaseReference.push().setValue(admin);
            Toast.makeText(this,"Data inserted successfully!",Toast.LENGTH_LONG).show();


        }
        catch(Exception e)
        {

            System.out.println(e.getMessage());
        }


    }
    protected void setUpAllUi()
    {

        loginButon=(Button)findViewById(R.id.adminLoginButton);
        editEmail=(EditText)findViewById(R.id.editTextEmailAdmin);
        editPassword=(EditText)findViewById(R.id.editTextPasswordAdmin);
    }
}