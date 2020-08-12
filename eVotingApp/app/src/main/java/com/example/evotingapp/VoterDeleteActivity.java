package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class VoterDeleteActivity extends AppCompatActivity {


    private Button confirmDelete;
    private EditText deletePassword;
    private TextView displayDeletingUsername;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_delete);

        setUpAllUi();
        Intent intent=getIntent();
        displayDeletingUsername.setText("Deleting: "+intent.getStringExtra("username"));

        confirmDelete.setOnClickListener(new View.OnClickListener() {
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
    public void checkForAdminLogin(final String Username, final String Password, final String VoterUsername)
    {


        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot snapshot1 :snapshot.getChildren()) {

                    Admin admin = snapshot1.getValue(Admin.class);


                    if((Username==null||Username.equalsIgnoreCase(admin.Username))&&Password.equalsIgnoreCase(admin.Password))
                    {
                        deleteVoter(VoterUsername);
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
    protected void deleteVoter(String Username)
    {


        DatabaseReference dbVoter = FirebaseDatabase.getInstance().getReference();

        Query applesQuery = dbVoter.child("Voter").orderByChild("Email").equalTo(Username);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {

                    Voter voter=appleSnapshot.getValue(Voter.class);
                    appleSnapshot.getRef().removeValue();
                }
                Toast.makeText(getApplicationContext(),"Voter deleted successfully!!",Toast.LENGTH_LONG).show();
                deletePassword.setText("");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Voter not deleted...",Toast.LENGTH_LONG).show();
            }
        });

    }
    protected void setUpAllUi(){

        confirmDelete=(Button)findViewById(R.id.voterDeleteConfirmButton);
        deletePassword=(EditText)findViewById(R.id.voterDeletePassword);
        displayDeletingUsername=(TextView)findViewById(R.id.voterDeleteEmail);
    }
}