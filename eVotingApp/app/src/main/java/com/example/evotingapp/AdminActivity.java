package com.example.evotingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminActivity extends AppCompatActivity {

    protected Button loginButon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        setUpAllUi();

        loginButon.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                dataBaseOperations();
            }
        });

    }
    public void dataBaseOperations()
    {

        try {



            Class.forName("com.mysql.jdbc.Driver");

            Connection connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3308/mysql?autoReconnect=true&useSSL=false","root","");

            Statement statement = connect.createStatement();
            // Result set get the result of the SQL query
           ResultSet resultSet = statement.executeQuery("select * from admin");
           while(resultSet.next())
           {

               System.out.println("Username: "+resultSet.getString(1));
           }

        }
        catch(Exception e)
        {

            System.out.println(e.getMessage());
        }


    }
    protected void setUpAllUi()
    {

        loginButon=(Button)findViewById(R.id.adminLoginButton);
    }
}