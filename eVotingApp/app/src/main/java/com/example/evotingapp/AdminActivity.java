package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    protected Button loginButon;
    DatabaseReference databaseReference;
    protected EditText editEmail;
    protected EditText editPassword;
    public static long MaxId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        setUpAllUi();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                    MaxId=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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


            int Id=(int)MaxId;
            String Username=editEmail.getText().toString().trim();
            String Password=editPassword.getText().toString().trim();

            Admin admin=new Admin(Id+1,Username,Password);
            //System.out.println(admin.Password);

            databaseReference.child(String.valueOf(MaxId+1)).setValue(admin);
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