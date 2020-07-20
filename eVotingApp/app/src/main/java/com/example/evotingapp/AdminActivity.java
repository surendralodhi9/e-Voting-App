package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    protected DatabaseReference databaseReference;
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

                String Username=editEmail.getText().toString().trim();
                String Password=editPassword.getText().toString().trim();

                if(Username.length()==0||Password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all details...",Toast.LENGTH_LONG).show();
                    return;
                }
                //dataBaseOperations();

                checkForAdminLogin(Username,Password);
            }
        });

    }
    public void checkForAdminLogin(final String Username, final String Password)
    {



        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot snapshot1 :snapshot.getChildren()) {

                    Admin admin = snapshot1.getValue(Admin.class);

                    if(Username.equalsIgnoreCase(admin.Username)&&Password.equalsIgnoreCase(admin.Password))
                    {


                        Intent intent=new Intent(getApplicationContext(),AdminHomeActivity.class);
                        intent.putExtra("Username",Username);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Login success!!",Toast.LENGTH_LONG).show();
                        return;
                    }


                }
                Toast.makeText(getApplicationContext(),"Please enter correct username and password",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

        loginButon=(Button)findViewById(R.id.voterLoginButton);
        editEmail=(EditText)findViewById(R.id.editTextEmailAdmin);
        editPassword=(EditText)findViewById(R.id.editTextPasswordAdmin);
    }
}