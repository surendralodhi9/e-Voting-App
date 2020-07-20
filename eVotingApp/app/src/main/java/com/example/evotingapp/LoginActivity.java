package com.example.evotingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private EditText voterEmail;
    private EditText voterPassword;
    private Button loginButton;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth;
    private  Button signOutButton;
    private int RC_SIGN_IN=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpAllUi();
        mAuth=FirebaseAuth.getInstance();
        GoogleSignInOptions gsio=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(this,gsio);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();
            }
        });
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* for sign out*/
                googleSignInClient.signOut();
                signOutButton.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Signed out successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void signIn()
    {

        Intent signInIntent=googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completeTask){

        try{

            GoogleSignInAccount acc=completeTask.getResult(ApiException.class);
            Toast.makeText(getApplicationContext(),"Signed in successfully",Toast.LENGTH_LONG).show();
            FirebaseGoogleAuth(acc);
        }
        catch(ApiException e)
        {
            Toast.makeText(getApplicationContext(),"Signed in unsuccessfully",Toast.LENGTH_LONG).show();

            FirebaseGoogleAuth(null);
        }
    }
    private void FirebaseGoogleAuth(GoogleSignInAccount acct)
    {

        AuthCredential authCredential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_LONG).show();
                    FirebaseUser user=mAuth.getCurrentUser();
                    updateUi(user);
                }
                else{

                    Toast.makeText(getApplicationContext(),"Unsuccessfully",Toast.LENGTH_LONG).show();
                    updateUi(null);
                }


            }
        });
    }
    private void updateUi(FirebaseUser user)
    {

        signOutButton.setVisibility(View.VISIBLE);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account!=null)
        {
            String personName=account.getDisplayName();
            String personGivenName=account.getGivenName();
            String personFamilyName=account.getFamilyName();
            String personEmail=account.getEmail();
            String personId=account.getId();
            Uri personPhoto=account.getPhotoUrl();

            Toast.makeText(getApplicationContext(),"Name: "+personName,Toast.LENGTH_SHORT).show();

        }
    }
    protected void setUpAllUi()
    {

        voterEmail=(EditText)findViewById(R.id.editTextEmailLogin);
        voterPassword=(EditText)findViewById(R.id.editTextPasswordLogin);
        loginButton=(Button)findViewById(R.id.voterLoginButton);
        signOutButton=(Button)findViewById(R.id.voterSignOutButton);
    }
}