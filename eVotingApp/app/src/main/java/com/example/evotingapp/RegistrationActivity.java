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

public class RegistrationActivity extends AppCompatActivity {


    private EditText UsernameText;
    private EditText NameText;
    private EditText FatherNameText;
    private EditText ConstituencyText;
    private EditText SignText;
    private Button submitButton;

    public static long MaxId=0;
    protected DatabaseReference databaseReference;
    protected DatabaseReference dbReferenceResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setUpAllUi();
        dbReferenceResult=FirebaseDatabase.getInstance().getReference().child("Result");

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Candidate");
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

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username=UsernameText.getText().toString().trim();
                String Name=NameText.getText().toString().trim();
                String FatherName=FatherNameText.getText().toString().trim();
                String Constituency=ConstituencyText.getText().toString().trim();
                String Sign=SignText.getText().toString().trim();

                if(Username.length()==0||Name.length()==0||FatherName.length()==0||Constituency.length()==0||Sign.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details...",Toast.LENGTH_LONG).show();
                    return;
                }
                Candidate candidate=new Candidate(Username,Name,FatherName,Constituency,Sign);
                Result result=new Result(Username);

                databaseReference.child(String.valueOf(MaxId+1)).setValue(candidate);
                dbReferenceResult.child(String.valueOf(MaxId+1)).setValue(result);
                Toast.makeText(getApplicationContext(),"Candidate registered successfully!!",Toast.LENGTH_LONG).show();
                UsernameText.setText("");
                NameText.setText("");
                FatherNameText.setText("");
                ConstituencyText.setText("");
                SignText.setText("");

            }
        });
    }
    protected void setUpAllUi()
    {
        UsernameText=(EditText)findViewById(R.id.updateEmail);
        NameText=(EditText)findViewById(R.id.updateName);
        FatherNameText=(EditText)findViewById(R.id.updateFatherName);
        ConstituencyText=(EditText)findViewById(R.id.updateConstituency);
        SignText=(EditText)findViewById(R.id.updateSign);
        submitButton=(Button)findViewById(R.id.saveButton);
    }
}