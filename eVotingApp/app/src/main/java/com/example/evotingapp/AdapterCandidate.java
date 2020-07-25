package com.example.evotingapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.evotingapp.R.raw.button_click_sound;
import static java.lang.Thread.sleep;

public class AdapterCandidate extends ArrayAdapter<Candidate> {

    ArrayList<Candidate> candidatesList = new ArrayList<>();
    private Context contextLogin;
    private int clicked;

    public AdapterCandidate(Context context, int textViewResourceId, ArrayList<Candidate> objects) {
        super(context, textViewResourceId, objects);
        contextLogin=context;
        candidatesList = objects;
        clicked=0;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_all_candidate_list, null);
        TextView signView = (TextView) v.findViewById(R.id.signVotecastView);
        final Button voteButton = (Button) v.findViewById(R.id.voteButton);
        TextView nameView=(TextView)v.findViewById(R.id.nameVotecastView);


        nameView.setText(candidatesList.get(position).Name+"\n"+candidatesList.get(position).Username);
        signView.setText(candidatesList.get(position).Sign);
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.button_click_sound);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clicked==1) {

                    contextLogin.startActivity(VotecastActivity.intentLogOut);
                    return;
                }

                try {
                    mp.start();
                    VotecastActivity.updateVotingStatus();
                    updateVoteCount(String.valueOf(position+1),candidatesList.get(position).Username);
                    clicked=1;
                    sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contextLogin.startActivity(VotecastActivity.intentLogOut);

                //System.out.println("Voted.................: "+position);


            }
        });
        return v;

    }
    public void updateVoteCount(final String position, final String username)
    {
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Result").child(position);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int votes=Integer.parseInt(snapshot.child("Votes").getValue().toString());
                DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("Result");
                Result res=new Result(username);
                res.Votes=votes+1;
                db.child(position).setValue(res);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
