package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class displayVoterActivity extends AppCompatActivity {

    private EditText gmailEdit;
    private GridView displayVoterGrid;
    private Button voterUpdateButton;
    DatabaseReference databaseReference;
    private ArrayList<Voter> votersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_voter);

        setUpAllUi();
        votersList=new ArrayList<>();
        addDataIntoList();
        displayVoterGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String username=votersList.get(i).Email;
                gmailEdit.setText(username);
            }
        });
        voterUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=gmailEdit.getText().toString().trim();
                if(user.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please select/enter usename",Toast.LENGTH_LONG).show();
                    return;
                }
                Voter voter=getSelectedVoter(user);
                if(voter==null)
                {
                    Toast.makeText(getApplicationContext(),"No such user exist!!",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent=new Intent(getApplicationContext(),updateVoterActivity.class);

                intent.putExtra("voter", voter);
                startActivity(intent);

            }
        });

    }
    protected Voter getSelectedVoter(String user)
    {

        for(int i=0;i<votersList.size();i++)
        {

            if(user.equalsIgnoreCase(votersList.get(i).Email))
            {
                return votersList.get(i);
            }
        }
        return null;
    }
    protected void addDataIntoList()
    {

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Voter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Voter voter=dataSnapshot.getValue(Voter.class);
                    votersList.add(voter);
                }
                VoterAdapter voterAdapter=new VoterAdapter(getApplicationContext(),R.layout.grid_view_voter,votersList);
                displayVoterGrid.setAdapter(voterAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    protected void setUpAllUi()
    {
        gmailEdit=(EditText)findViewById(R.id.updateVoterEmail);
        displayVoterGrid=(GridView)findViewById(R.id.voterDisplayGrid);
        voterUpdateButton=(Button)findViewById(R.id.voterUpdateButton);

    }
}