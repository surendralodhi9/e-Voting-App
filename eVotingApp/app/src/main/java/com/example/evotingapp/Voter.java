package com.example.evotingapp;

import java.io.Serializable;

public class Voter implements Serializable {


    public  long Id;
    public String Email;
    public String Name;
    public int Age;
    public String Constituency;
    public boolean Voted;

    public Voter()
    {

    }
    public Voter(long Id,String Email,String Name,int Age,String Constituency)
    {

        this.Id=Id;
        this.Email=Email;
        this.Name=Name;
        this.Voted=false;
        this.Age=Age;
        this.Constituency=Constituency;
    }
}
