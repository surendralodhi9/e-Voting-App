package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ModifyCandidateActivity extends AppCompatActivity {


    private EditText Username;
    private Button viewButton;
    private Button deleteButton;
    private Button updateButton;
    private GridView displayGrid;

    private ArrayList<Candidate> candidatesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_candidate);


        setUpAllUi();
        candidatesList=new ArrayList<>();
        Candidate candidate=new Candidate("surendralodhi9","Surendra","Imrat","Pichhore","Hand");
        Candidate candidate1=new Candidate("surendralodhi","SurendraLodhi","Imrat","Pichhore","Hand");
        Candidate candidate2=new Candidate("surendralodhi","SurendraLodhi","Imrat","Pichhore","Hand");
        candidatesList.add(candidate);
        candidatesList.add(candidate1);
        candidatesList.add(candidate2);
        candidatesList.add(candidate);
        candidatesList.add(candidate1);
        candidatesList.add(candidate2);
        candidatesList.add(candidate);
        candidatesList.add(candidate1);
        candidatesList.add(candidate2);
        candidatesList.add(candidate);
        candidatesList.add(candidate1);
        candidatesList.add(candidate2);
        candidatesList.add(candidate);
        candidatesList.add(candidate1);
        candidatesList.add(candidate2);






        MyAdapter myAdapter=new MyAdapter(this,R.layout.grid_view_candidates,candidatesList);
        displayGrid.setAdapter(myAdapter);

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