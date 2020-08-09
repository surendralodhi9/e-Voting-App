package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
    public List<Candidate> list;

    public static int conId;
    public Spinner spinner;
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setUpAllUi();
        list=new ArrayList<>();
        intializeDatabase();
        addSpinner();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username=UsernameText.getText().toString().trim();
                String Name=NameText.getText().toString().trim();
                String FatherName=FatherNameText.getText().toString().trim();
                String Constituency=RegisterVoterActivity.constituencyList[conId];
                String Sign=SignText.getText().toString().trim();

                if(Username.length()==0||Name.length()==0||FatherName.length()==0||Constituency.length()==0||Sign.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details...",Toast.LENGTH_LONG).show();
                    return;
                }
                if(conId==0)
                {
                    Toast.makeText(getApplicationContext(),"Please select constituency!!",Toast.LENGTH_LONG).show();
                    return;
                }

                if(alreadyExist(Username,Constituency)) {
                        Toast.makeText(getApplicationContext(), "Username/Email already exist..", Toast.LENGTH_LONG).show();
                        return;
                    }

                intializeId();
                Candidate candidate=new Candidate(MaxId+1,Username,Name,FatherName,Constituency,Sign);
                Result result=new Result(MaxId+1,Username);

                databaseReference.child(String.valueOf(MaxId+1)).setValue(candidate);
                dbReferenceResult.child(String.valueOf(MaxId+1)).setValue(result);
                Toast.makeText(getApplicationContext(),"Candidate registered successfully!!",Toast.LENGTH_LONG).show();
                UsernameText.setText("");
                NameText.setText("");
                FatherNameText.setText("");
                addSpinner();
                SignText.setText("");
                //intializeDatabase();

            }
        });
    }
    protected void intializeDatabase(){

        dbReferenceResult=FirebaseDatabase.getInstance().getReference().child("Result");

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Candidate");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list=new ArrayList<>();
                if(snapshot.exists())
                    MaxId=(snapshot.getChildrenCount());
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Candidate candidate=dataSnapshot.getValue(Candidate.class);


                    list.add(candidate);
                }
                System.out.println("Callinnnnnnggggggg");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void intializeId(){

        for(int i=0;i<list.size();i++)
        {

            //System.out.print("List  "+list.get(i).Id);
            if(list.get(i).Id!=i+1) {
                MaxId = i;
                return;
            }
        }
        MaxId=list.size();
    }
    protected void addSpinner()
    {
        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_list,RegisterVoterActivity.constituencyList);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {


            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                conId=position;
                //voterEmail.setText(constituencyList[conId]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }
    protected boolean alreadyExist(String Username,String Constituency)  {


        for(int i=0;i<list.size();i++)
        {

            if(Username.equalsIgnoreCase(list.get(i).Username)&& Constituency.equalsIgnoreCase(list.get(i).Constituency)){

                return true;
            }
        }
            return false;
    }
    protected void setUpAllUi()
    {
        UsernameText=(EditText)findViewById(R.id.updateEmail);
        NameText=(EditText)findViewById(R.id.updateName);
        FatherNameText=(EditText)findViewById(R.id.updateFatherName);
        spinner=(Spinner) findViewById(R.id.candidateConstituencySpinner);
        SignText=(EditText)findViewById(R.id.updateSign);
        submitButton=(Button)findViewById(R.id.saveButton);
    }
}