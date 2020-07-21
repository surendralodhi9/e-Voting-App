package com.example.evotingapp;

import java.io.Serializable;

public class Voter implements Serializable {


    public String Email;
    public String Name;
    public int Age;
    public String Constituency;
    public boolean Voted;

    public Voter()
    {

    }
    public Voter(String Email,String Name,int Age,String Constituency)
    {

        this.Email=Email;
        this.Name=Name;
        this.Voted=false;
        this.Age=Age;
        this.Constituency=Constituency;
    }
}
