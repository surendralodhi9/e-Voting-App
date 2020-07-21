package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VotecastActivity extends AppCompatActivity {

    private TextView voterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votecast);


        setUpAllUi();

        Intent intent=getIntent();
        Voter voter=(Voter)intent.getSerializableExtra("voter");
        voterName.setText(voter.Name);
        updateVotingStatus(voter);
    }

    protected void updateVotingStatus(Voter voter)
    {

        voter.Voted=true;
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Voter");
        databaseReference.child(voter.Email).setValue(voter);
        Toast.makeText(getApplicationContext(),"Congratulations!! you casted your vote",Toast.LENGTH_LONG).show();
    }
    protected void setUpAllUi(){


        voterName=(TextView)findViewById(R.id.voterNameView);
    }

}