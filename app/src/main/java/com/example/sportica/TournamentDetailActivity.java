package com.example.sportica;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TournamentDetailActivity extends AppCompatActivity {

    ImageView tim1;
    TextView tt1;
    TextView tt2;
    Button tb1;
    Button tb2;
    Button tb3;
    Button tb4;
    DatabaseReference ref;
    public String key;
    String title;
    String detail;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_detail);

        tim1 = this.findViewById(R.id.tim1);
        tt1 = this.findViewById(R.id.tt1);
        tt2 = this.findViewById(R.id.tt2);
        tb1 = this.findViewById(R.id.tb1);
        tb2 = this.findViewById(R.id.tb2);
        tb3 = this.findViewById(R.id.tb3);
        tb4 = this.findViewById(R.id.tb4);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        key = intent.getStringExtra("key");
        new NavActivity.DownloadImage(tim1).execute(url);

        ref = FirebaseDatabase.getInstance().getReference().child("tournament").child(key);
        readFromDatabase();
    }

    public void readFromDatabase(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tt1.setText(dataSnapshot.child("title").getValue().toString());
                tt1.setTextSize(30);
                tt2.setText(dataSnapshot.child("detail").getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(parentIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sportClick(View v){
        Intent intent = new Intent(this, TournamentSportActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }
    public void medalsClick(View v){
        Intent intent = new Intent(this, TournamentMedalsActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }
    public void playerClick(View v){
        Intent intent = new Intent(this, TournamentPlayerActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }
    public void contactClick(View v){
        Intent intent = new Intent(this, TournamentContactActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }
}
