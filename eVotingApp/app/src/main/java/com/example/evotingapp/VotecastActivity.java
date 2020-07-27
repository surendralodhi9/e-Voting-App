package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VotecastActivity extends AppCompatActivity {

    private TextView voterName;
    public DatabaseReference databaseReference;
    public Button voteButton;
    private ArrayList<Candidate> candidatesList;
    private GridView displayVotecastGrid;
    public static Voter voter;
    public static Intent intentLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votecast);


        setUpAllUi();
        candidatesList=new ArrayList<>();

        Intent intent=getIntent();
        voter=(Voter)intent.getSerializableExtra("voter");
        intentLogOut=new Intent(getApplicationContext(),LoginActivity.class);
        intentLogOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        voterName.setText(voter.Name);
        addDataInList();
        //updateVotingStatus(voter,intent.getStringExtra("voterid"));
    }
    public static void updateVotingStatus()
    {
        voter.Voted=true;
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Voter");
        databaseReference.child(String.valueOf(voter.Id)).setValue(voter);

        //Toast.makeText(getApplicationContext(),"Congratulations!! you casted your vote",Toast.LENGTH_LONG).show();
    }
    protected void addDataInList()
    {

        System.out.println("Calling addDataInList: "+candidatesList.size());
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Candidate");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Candidate candidate=dataSnapshot.getValue(Candidate.class);
                    candidatesList.add(candidate);

                }
                AdapterCandidate myAdapter=new AdapterCandidate(getApplicationContext(),R.layout.grid_view_all_candidate_list,candidatesList);
                displayVotecastGrid.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    protected void setUpAllUi(){

        voterName=(TextView)findViewById(R.id.voterNameView);
        displayVotecastGrid=(GridView)findViewById(R.id.displayVotecastGrid);
    }

}