package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateVoterActivity extends AppCompatActivity {

    private TextView emailView;
    private EditText nameText;
    private EditText ageText;
    private Button voterUpdateSaveButton;
    public static int conId;
    public Spinner spinner;
    public static long voterId=0;

    private DatabaseReference databaseReference;
    public ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_voter);

        setUpAllUi();
        addSpinner();

        Intent intent=getIntent();

        Voter voter=(Voter) intent.getSerializableExtra("voter");
        for(int i=0;i<RegisterVoterActivity.constituencyList.length;i++)
        {
            if(voter.Constituency.equalsIgnoreCase(RegisterVoterActivity.constituencyList[i]))
            {
                conId=i;
                break;
            }
        }
        voterId=voter.Id;
        emailView.setText(voter.Email);
        nameText.setText(voter.Name);
        ageText.setText(String.valueOf(voter.Age));
        spinner.setSelection(conId);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Voter");



        voterUpdateSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Username=emailView.getText().toString().trim();
                String Name=nameText.getText().toString().trim();
                String Age=ageText.getText().toString().trim();


                String Constituency= RegisterVoterActivity.constituencyList[conId];

                if(Username.length()==0||Name.length()==0||Constituency.length()==0||Age.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the details...",Toast.LENGTH_LONG).show();
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

                Voter voter=new Voter(voterId,Username,Name,voterage,Constituency);

                databaseReference.child(String.valueOf(voterId)).setValue(voter);
                Toast.makeText(getApplicationContext(),"Details updated successfully!!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),displayVoterActivity.class);
                startActivity(intent);

            }
        });
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
    protected void setUpAllUi()
    {

        emailView=(TextView)findViewById(R.id.updateVoterEmailUsername);
        nameText=(EditText)findViewById(R.id.updateVoterName);
        ageText=(EditText)findViewById(R.id.updateVoterAge);
        voterUpdateSaveButton=(Button)findViewById(R.id.saveVoterUpdateButton);
        spinner=(Spinner)findViewById(R.id.updateVoterConstituencySpinner);
    }
}