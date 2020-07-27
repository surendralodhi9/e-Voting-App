package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterVoterActivity extends AppCompatActivity {


    public EditText voterEmail;
    public EditText voterName;
    public EditText voterAge;
    private Button saveVoterButton;

    public static int conId;
    public static String []constituencyList={"Select","Pichhore","Karera","Shivpuri","Kaularas","Khaniadhana"};

    public static long MaxId=0;
    public Spinner spinner;
    public ArrayAdapter<String> adapter;
    public DatabaseReference databaseReference;

    public List<Voter> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_voter);

        setUpAllUi();
        list=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Voter");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                    MaxId=(snapshot.getChildrenCount());
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Voter voter=dataSnapshot.getValue(Voter.class);

                    list.add(voter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_list,constituencyList);
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
        saveVoterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name=voterName.getText().toString().trim();
                String Email=voterEmail.getText().toString().trim();
                String Age=voterAge.getText().toString().trim();

                if(Name.length()==0||Email.length()==0||Age.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all details!!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(conId==0)
                {
                    Toast.makeText(getApplicationContext(),"Please select constituency!!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!goodFormate(Age))
                {
                    Toast.makeText(getApplicationContext(),"Please enter only number for age!!",Toast.LENGTH_LONG).show();
                    return;
                }
                int voterage=Integer.parseInt(Age);
                if(voterage<18)
                {
                    Toast.makeText(getApplicationContext(),"Voter must be at least 18 years old!!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(alreadyExist(Email))
                {
                    Toast.makeText(getApplicationContext(),"Voter already exist of this username...",Toast.LENGTH_LONG).show();
                    return;
                }
                Voter voter=new Voter(MaxId+1,Email,Name, voterage,constituencyList[conId]);


                databaseReference.child(String.valueOf(MaxId+1)).setValue(voter);
                Toast.makeText(getApplicationContext(),"Voter registered successfully!!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),RegisterVoterActivity.class);
                //startActivity(intent);
            }
        });

    }
    protected boolean alreadyExist(String Email)
    {

        for(int i=0;i<list.size();i++)
        {

            if(Email.equalsIgnoreCase(list.get(i).Email)){

                return true;
            }
        }
        return false;
    }

    protected boolean goodFormate(String age)
    {

        for(int i=0;i<age.length();i++)
        {

            if(age.charAt(i)<'0'||age.charAt(i)>'9')
                return false;
        }
        return true;
    }
    protected void setUpAllUi()
    {

        voterEmail=(EditText)findViewById(R.id.voterEmail);
        voterName=(EditText)findViewById(R.id.voterName);
        voterAge=(EditText)findViewById(R.id.voterAge);
        spinner=(Spinner)findViewById(R.id.voterConstituencySpinner);
        saveVoterButton=(Button)findViewById(R.id.saveVoterButton);
    }
}