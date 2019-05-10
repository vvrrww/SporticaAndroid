package com.example.sportica;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TournamentPlayerActivity extends AppCompatActivity {

    String tname;
    LinearLayout pll1;
    DatabaseReference ref;
    Button fb1;
    ArrayList<String> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_player);
        pll1 = this.findViewById(R.id.pll1);
        fb1 = this.findViewById(R.id.fb1);
        Intent intent = getIntent();
        tname = intent.getStringExtra("tname");
        ref = FirebaseDatabase.getInstance().getReference().child("tournament2/"+tname+"/sport");
        readFromDatabase();
    }
    public void readFromDatabase(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pll1.removeAllViews();
                cars = new ArrayList<String>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    final String sname = snapshot.getKey().toString();

                    TextView t1 = new TextView(getApplicationContext());
                    t1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    t1.setTextSize(30);
                    t1.setText(sname);
                    pll1.addView(t1);
                    cars.add(sname);

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        final String fname = snapshot1.getKey().toString();

                        TextView t2 = new TextView(getApplicationContext());
                        t2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        t2.setTextSize(20);
                        t2.setText(fname);
                        pll1.addView(t2);

                        for (DataSnapshot snapshot2 : snapshot1.getChildren()) {
                            final String pname = snapshot2.getKey().toString();
                            final String type = snapshot2.getValue().toString();

                            TextView t3 = new TextView(getApplicationContext());
                            t3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            t3.setTextSize(20);
                            t3.setText(pname);
                            pll1.addView(t3);

                            TextView t4 = new TextView(getApplicationContext());
                            t4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                            t4.setText("Type : " + type);
                            pll1.addView(t4);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void playerFormClick(View v){
        Intent intent = new Intent(this, TournamentPlayerFormActivity.class);
//        intent.putStringArrayListExtra("cars", cars);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                final String sport = data.getStringExtra("sport");
                final String faculty = data.getStringExtra("faculty");
                final String name = data.getStringExtra("name");
                final String type = data.getStringExtra("type");

                DatabaseReference newRef = FirebaseDatabase.getInstance().getReference().child("tournament2/"+tname+"/sport/"+sport+"/"+faculty+"/"+name);
                newRef.setValue(type);

                Toast.makeText(this, "Form apply successfully", Toast.LENGTH_LONG);
            }
            if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Form has been cancelled", Toast.LENGTH_LONG);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
