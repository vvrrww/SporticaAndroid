package com.example.sportica;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.net.URL;

public class NewsFragment extends Fragment {

    TextView t1;
    DatabaseReference ref;

    public NewsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t1 = getView().findViewById(R.id.t1);
        t1.setText("News");
        ref = FirebaseDatabase.getInstance().getReference().child("news");
        readFromDatabase();
    }

    public void readFromDatabase(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                LinearLayout ll1 = getView().findViewById(R.id.ll1);
                ll1.removeAllViews();
                String bs = "more detail...";

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String title = snapshot.child("title").getValue().toString();
                    final String date = snapshot.child("date").getValue().toString();
                    final String url = snapshot.child("image").getValue().toString();
                    final String short_detail = snapshot.child("short_detail").getValue().toString();
                    final String long_detail = snapshot.child("long_detail").getValue().toString();


                    ImageView imv = new ImageView(getActivity());
                    LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp1.topMargin = 50;
                    lp1.height = 500;
                    imv.setLayoutParams(lp1);
                    new NavActivity.DownloadImage(imv).execute(url);
                    ll1.addView(imv);

                    TextView t1 = new TextView(getActivity());
                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    t1.setLayoutParams(lp2);
                    t1.setTextSize(30);
                    t1.setText(title);
                    ll1.addView(t1);

                    TextView t2 = new TextView(getActivity());
                    LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    t2.setLayoutParams(lp3);
                    t2.setText(short_detail);
                    ll1.addView(t2);

                    TextView t3 = new TextView(getActivity());
                    LinearLayout.LayoutParams lp5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    t3.setLayoutParams(lp5);
                    t3.setText(date);
                    ll1.addView(t3);

                    Button btn = new Button(getActivity());
                    btn.setText(bs);
                    LinearLayout.LayoutParams lp4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp4.setMarginStart(800);
                    btn.setLayoutParams(lp4);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
                            intent.putExtra("title", title);
                            intent.putExtra("url", url);
                            intent.putExtra("long_detail", long_detail);
                            intent.putExtra("date", date);
                            startActivity(intent);
                        }
                    });

                    ll1.addView(btn);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

}
