package com.example.evotingapp;

import java.io.Serializable;

public class Candidate implements Serializable {

    public long Id;
    public String Username;
    public String Name;
    public String FatherName;
    public String Constituency;
    public String Sign;
    public Candidate()
    {

    }
    public Candidate(long Id,String Username,String Name,String FatherName,String Constituency,String Sign)
    {
        this.Id=Id;
        this.Username=Username;
        this.Name=Name;
        this.FatherName=FatherName;
        this.Constituency=Constituency;
        this.Sign=Sign;
    }

}
