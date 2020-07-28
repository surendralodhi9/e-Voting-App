package com.example.evotingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VoterAdapter extends ArrayAdapter<Voter> {

    ArrayList<Voter> votersList = new ArrayList<>();

    public VoterAdapter(Context context, int textViewResourceId, ArrayList<Voter> objects) {
        super(context, textViewResourceId, objects);
        votersList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_voter, null);
        TextView nameView = (TextView) v.findViewById(R.id.voterNameView);
        TextView constituencyView = (TextView) v.findViewById(R.id.voterConstituencyView);
        TextView usernameView=(TextView)v.findViewById(R.id.voterUsernameView);
        TextView ageView=(TextView)v.findViewById(R.id.voterAgeView);
        usernameView.setText(votersList.get(position).Email);

        nameView.setText(votersList.get(position).Name);
        constituencyView.setText(votersList.get(position).Constituency);
        ageView.setText(String.valueOf(votersList.get(position).Age));
        return v;

    }

}
