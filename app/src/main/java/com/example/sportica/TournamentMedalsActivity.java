package com.example.sportica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.sql.DriverManager.println;

public class TournamentMedalsActivity extends AppCompatActivity {

    String key;
    LinearLayout mll1;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_medals);

        mll1 = this.findViewById(R.id.mll1);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        ref = FirebaseDatabase.getInstance().getReference().child("tournament").child(key).child("medal");

        readFromDatabase();
    }

    public void readFromDatabase(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mll1.removeAllViews();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String faculty = snapshot.child("faculty").getValue().toString();
                    final String gold = snapshot.child("gold").getValue().toString();
                    final String silver = snapshot.child("silver").getValue().toString();
                    final String bronze = snapshot.child("bronze").getValue().toString();

                    TextView t0 = new TextView(getApplicationContext());
                    t0.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t0.setTextSize(30);
                    t0.setText(faculty);
                    mll1.addView(t0);

                    TextView t1 = new TextView(getApplicationContext());
                    t1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t1.setTextSize(20);
                    t1.setText("Gold Medal : " + gold + "medal(s)");
                    mll1.addView(t1);

                    TextView t2 = new TextView(getApplicationContext());
                    t2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t2.setTextSize(20);
                    t2.setText("Silver Medal : " + silver + "medal(s)");
                    mll1.addView(t2);

                    TextView t3 = new TextView(getApplicationContext());
                    t3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t3.setTextSize(20);
                    t3.setText("Bronze Medal : " + bronze + "medal(s)");
                    mll1.addView(t3);
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
