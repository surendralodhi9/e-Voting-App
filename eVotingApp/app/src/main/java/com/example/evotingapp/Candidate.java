package com.example.evotingapp;

import java.io.Serializable;

public class Candidate implements Serializable {


    public String Username;
    public String Name;
    public String FatherName;
    public String Constituency;
    public String Sign;

    public Candidate()
    {

    }

    public Candidate(String Username,String Name,String FatherName,String Constituency,String Sign)
    {

        this.Username=Username;
        this.Name=Name;
        this.FatherName=FatherName;
        this.Constituency=Constituency;
        this.Sign=Sign;
    }



}
