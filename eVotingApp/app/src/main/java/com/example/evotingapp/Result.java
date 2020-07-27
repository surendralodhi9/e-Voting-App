package com.example.evotingapp;

import java.io.Serializable;

public class Result implements Serializable {

    public long Id;
    public String Username;
    public int Votes;

    public Result()
    {

    }

    public Result(long Id,String Username)
    {
        this.Id=Id;
        this.Username=Username;
        this.Votes=0;
    }
}
