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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterVoterActivity extends AppCompatActivity {


    public EditText voterEmail;
    public EditText voterName;
    public EditText voterAge;
    private Button saveVoterButton;

    public static int conId;
    public static String []constituencyList={"Select","Pichhore","Karera","Shivpuri","Kaularas","Khaniadhana"};

    public Spinner spinner;
    public ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_voter);

        setUpAllUi();



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
                Voter voter=new Voter(Email,Name, voterage,constituencyList[conId]);

                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Voter");
                databaseReference.child(Email).setValue(voter);
                Toast.makeText(getApplicationContext(),"Voter registered successfully!!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),RegisterVoterActivity.class);
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
    protected void setUpAllUi()
    {

        voterEmail=(EditText)findViewById(R.id.voterEmail);
        voterName=(EditText)findViewById(R.id.voterName);
        voterAge=(EditText)findViewById(R.id.voterAge);
        spinner=(Spinner)findViewById(R.id.voterConstituencySpinner);
        saveVoterButton=(Button)findViewById(R.id.saveVoterButton);
    }
}