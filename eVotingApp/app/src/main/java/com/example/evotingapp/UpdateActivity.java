package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {

    private TextView updateUsernameText;
    private EditText updateNameText;
    private EditText updateFatherNameText;
    private EditText updateConstituencyText;
    private EditText updateSignText;
    private Button updateSaveButton;

    public static long CandidateId=0;
    protected DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setUpAllUi();
        Intent intent=getIntent();

        CandidateId=Long.parseLong(intent.getStringExtra("CandidateId"));

        Candidate candidate=(Candidate)intent.getSerializableExtra("candidate");
        updateUsernameText.setText(candidate.Username);
        updateFatherNameText.setText(candidate.FatherName);
        updateNameText.setText(candidate.Name);
        updateConstituencyText.setText(candidate.Constituency);
        updateSignText.setText(candidate.Sign);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Candidate");

        updateSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username=updateUsernameText.getText().toString().trim();
                String Name=updateNameText.getText().toString().trim();
                String FatherName=updateFatherNameText.getText().toString().trim();
                String Constituency=updateConstituencyText.getText().toString().trim();
                String Sign=updateSignText.getText().toString().trim();

                if(Username.length()==0||Name.length()==0||FatherName.length()==0||Constituency.length()==0||Sign.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details...",Toast.LENGTH_LONG).show();
                    return;
                }
                Candidate candidate=new Candidate(Username,Name,FatherName,Constituency,Sign);


                databaseReference.child(String.valueOf(CandidateId)).setValue(candidate);
                Toast.makeText(getApplicationContext(),"Details updated successfully!!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),ModifyCandidateActivity.class);
                startActivity(intent);
                //setUpdatedValue();
                //updateUsernameText.setText(d);
                //NameText.setText("");
                //FatherNameText.setText("");
                //ConstituencyText.setText("");

            }
        });
    }
    protected void setUpdatedValue()
    {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Candidate").child(String.valueOf(CandidateId));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                updateNameText.setText(snapshot.child("Name").getValue().toString());
                updateFatherNameText.setText(snapshot.child("FatherName").getValue().toString());
                updateConstituencyText.setText(snapshot.child("Constituency").getValue().toString());
                updateSignText.setText(snapshot.child("Sign").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    protected void setUpAllUi()
    {
        updateUsernameText=(TextView)findViewById(R.id.updateEmail);
        updateNameText=(EditText)findViewById(R.id.updateName);
        updateFatherNameText=(EditText)findViewById(R.id.updateFatherName);
        updateConstituencyText=(EditText)findViewById(R.id.updateConstituency);
        updateSignText=(EditText)findViewById(R.id.updateSign);
        updateSaveButton=(Button)findViewById(R.id.saveButton);
    }
}