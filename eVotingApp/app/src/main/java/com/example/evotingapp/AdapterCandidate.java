package com.example.evotingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterCandidate extends ArrayAdapter<Candidate> {

    ArrayList<Candidate> candidatesList = new ArrayList<>();

    public AdapterCandidate(Context context, int textViewResourceId, ArrayList<Candidate> objects) {
        super(context, textViewResourceId, objects);
        candidatesList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_all_candidate_list, null);
        TextView signView = (TextView) v.findViewById(R.id.signVotecastView);
        final Button voteButton = (Button) v.findViewById(R.id.voteButton);
        TextView nameView=(TextView)v.findViewById(R.id.nameVotecastView);


        nameView.setText(candidatesList.get(position).Name+"\n"+candidatesList.get(position).Username);
        signView.setText(candidatesList.get(position).Sign);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Voted.................");
            }
        });
        return v;

    }

}
