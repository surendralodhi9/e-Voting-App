package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    private TextView deleteUsername;
    private EditText deletePassword;
    private Button deleteConfirmButton;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        setUpAllUi();
        Intent intent=getIntent();
        deleteUsername.setText("Deleting: "+intent.getStringExtra("username"));

        deleteConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Password=deletePassword.getText().toString().trim();

                if(Password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent=getIntent();

                String adminusername=AdminHomeActivity.Adminusername;
                System.out.println("Admin   +"+adminusername);
                String Username=intent.getStringExtra("username");
                checkForAdminLogin(adminusername,Password,Username);

                }
        });
    }
    public void checkForAdminLogin(final String Username, final String Password, final String Candidateusername)
    {


        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot snapshot1 :snapshot.getChildren()) {

                    Admin admin = snapshot1.getValue(Admin.class);


                    if((Username==null||Username.equalsIgnoreCase(admin.Username))&&Password.equalsIgnoreCase(admin.Password))
                    {
                        deleteCandidate(Candidateusername);
                        deleteResult(Candidateusername);
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    protected void deleteCandidate(String Username)
    {


        DatabaseReference dbcandidate = FirebaseDatabase.getInstance().getReference();

        Query applesQuery = dbcandidate.child("Candidate").orderByChild("Username").equalTo(Username);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {

                    Candidate candidate=appleSnapshot.getValue(Candidate.class);

                    appleSnapshot.getRef().removeValue();

                }
                Toast.makeText(getApplicationContext(),"Candidate deleted successfully!!",Toast.LENGTH_LONG).show();
                deletePassword.setText("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Candidate not deleted...",Toast.LENGTH_LONG).show();
            }
        });

    }
    protected void deleteResult(String Username)
    {
        DatabaseReference dbcandidate = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = dbcandidate.child("Result").orderByChild("Username").equalTo(Username);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
                //.makeText(getApplicationContext(),"Candidate deleted successfully!!",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Result not deleted...",Toast.LENGTH_LONG).show();
            }
        });
    }
    protected void setUpAllUi()
    {

        deleteUsername=(TextView)findViewById(R.id.deleteEmail);
        deletePassword=(EditText)findViewById(R.id.deletePassword);
        deleteConfirmButton=(Button)findViewById(R.id.deleteConfirmButton);
    }
}