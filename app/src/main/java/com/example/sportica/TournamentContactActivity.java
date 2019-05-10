package com.example.sportica;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TournamentContactActivity extends AppCompatActivity {

    String tname;
    LinearLayout cll1;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_contact);
        cll1 = this.findViewById(R.id.cll1);
        Intent intent = getIntent();
        tname = intent.getStringExtra("tname");
        ref = FirebaseDatabase.getInstance().getReference().child("tournament2/"+tname+"/contact");
        readFromDatabase();
    }
    public void readFromDatabase(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cll1.removeAllViews();
                int n = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String name = snapshot.getKey().toString();
                    final String phone = snapshot.child("phone").getValue().toString();
                    final String position = snapshot.child("position").getValue().toString();

                    n++;

                    TextView t0 = new TextView(getApplicationContext());
                    t0.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t0.setTextSize(30);
                    t0.setText(Integer.toString(n));
                    cll1.addView(t0);

                    TextView t1 = new TextView(getApplicationContext());
                    t1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t1.setTextSize(20);
                    t1.setText(name);
                    cll1.addView(t1);

                    TextView t2 = new TextView(getApplicationContext());
                    t2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t2.setTextSize(20);
                    t2.setText(phone);
                    cll1.addView(t2);

                    TextView t3 = new TextView(getApplicationContext());
                    t3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t3.setTextSize(20);
                    t3.setText(position);
                    cll1.addView(t3);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
