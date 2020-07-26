package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    private TextView helpView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setUpAllUi();
        String message="1. Please click on Login button\n2. Ensure that you are a registered voter," +
                " If you are not registered then contact your BLO officer.\n3. Please login using your gmail account.\n" +
                "4. Press the button displayed  in front of the your preffered candidate.\n5. Once you press button you will hear the confirmation" +
                "sound.\n6. Now you can leave after the sound stopped.";
        helpView.setText(message);
    }
    protected void setUpAllUi()
    {
        helpView=(TextView)findViewById(R.id.helpView);

    }
}