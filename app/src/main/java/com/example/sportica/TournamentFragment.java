package com.example.sportica;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentFragment extends Fragment {

    TextView t1;
    DatabaseReference ref;

    public TournamentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_tournament, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t1 = getView().findViewById(R.id.t1);
        t1.setText("Tournament");
        ref = FirebaseDatabase.getInstance().getReference().child("tournament2");
        readFromDatabase();
    }

    public void readFromDatabase(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                LinearLayout ll1 = getView().findViewById(R.id.ll1);
                ll1.removeAllViews();
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String url = snapshot.child("image").getValue().toString();
                    final String tname = snapshot.getKey().toString();

                    ImageView imv = new ImageView(getActivity());
                    LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    lp1.topMargin = 50;
                    imv.setLayoutParams(lp1);
                    new NavActivity.DownloadImage(imv).execute(url);

                    imv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),TournamentDetailActivity.class);
                            intent.putExtra("url", url);
                            intent.putExtra("tname", tname);
                            startActivity(intent);
                        }
                    });

                    ll1.addView(imv);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
