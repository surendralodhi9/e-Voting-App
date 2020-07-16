package com.example.evotingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Candidate> {

    ArrayList<Candidate> candidatesList = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Candidate> objects) {
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
        v = inflater.inflate(R.layout.grid_view_candidates, null);
        TextView nameView = (TextView) v.findViewById(R.id.nameView);
        TextView signView = (TextView) v.findViewById(R.id.signView);
        nameView.setText(candidatesList.get(position).Name);
        signView.setText(candidatesList.get(position).Sign);
        return v;

    }

}
