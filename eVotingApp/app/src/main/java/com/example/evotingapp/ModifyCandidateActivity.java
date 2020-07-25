package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ModifyCandidateActivity extends AppCompatActivity {


    private EditText Username;
    private Button viewButton;
    private Button deleteButton;
    private Button updateButton;
    private GridView displayGrid;
    public DatabaseReference databaseReference;

    private ArrayList<Candidate> candidatesList;
    public static int CandidateId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_candidate);


        setUpAllUi();
        candidatesList=new ArrayList<>();

        addDataInList();




        displayGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String username=candidatesList.get(i).Username;

                Username.setText(username);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user=Username.getText().toString().trim();
                if(user.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please select/enter usename",Toast.LENGTH_LONG).show();
                    return;
                }

                Intent currentintent=getIntent();

                Intent intent=new Intent(getApplicationContext(),DeleteActivity.class);
                intent.putExtra("adminusername",currentintent.getStringExtra("adminusername"));
                intent.putExtra("username",user);
                startActivity(intent);
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=Username.getText().toString().trim();
                if(user.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please select/enter usename",Toast.LENGTH_LONG).show();
                    return;
                }

                Candidate candidate=getSelectedCandidate(user);
                if(candidate==null)
                {
                    Toast.makeText(getApplicationContext(),"No such user exist!!",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent=new Intent(getApplicationContext(),DisplayActivity.class);

                intent.putExtra("candidate", candidate);

                startActivity(intent);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user=Username.getText().toString().trim();
                if(user.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please select/enter usename",Toast.LENGTH_LONG).show();
                    return;
                }
                Candidate candidate=getSelectedCandidate(user);
                if(candidate==null)
                {
                    Toast.makeText(getApplicationContext(),"No such user exist!!",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent=new Intent(getApplicationContext(),UpdateActivity.class);

                Intent intent1=getIntent();
                intent.putExtra("adminusername",intent1.getStringExtra("adminusername"));
                intent.putExtra("candidate", candidate);
                intent.putExtra("CandidateId",String.valueOf(CandidateId));
                startActivity(intent);
            }
        });
    }
    protected Candidate getSelectedCandidate(String user)
    {
        for(int i=0;i<candidatesList.size();i++)
        {

            if(user.equalsIgnoreCase(candidatesList.get(i).Username)) {
                CandidateId = i + 1;
                return candidatesList.get(i);
            }
        }
        return null;


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
                MyAdapter myAdapter=new MyAdapter(getApplicationContext(),R.layout.grid_view_candidates,candidatesList);
                displayGrid.setAdapter(myAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    protected void setUpAllUi()
    {

        Username=(EditText)findViewById(R.id.updateEmail);
        viewButton=(Button)findViewById(R.id.viewButton);
        deleteButton=(Button)findViewById(R.id.deleteButton);
        updateButton=(Button)findViewById(R.id.updateButton);
        displayGrid=(GridView)findViewById(R.id.displayGrid);


    }
}