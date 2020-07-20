package com.example.evotingapp;

public class Voter {


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
