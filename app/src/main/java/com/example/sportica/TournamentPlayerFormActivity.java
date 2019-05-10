package com.example.sportica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TournamentPlayerFormActivity extends AppCompatActivity {

    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    Button apply_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_player_form);
        e1 = this.findViewById(R.id.e1);
        e2 = this.findViewById(R.id.e2);
        e3 = this.findViewById(R.id.e3);
        e4 = this.findViewById(R.id.e4);
        apply_button = this.findViewById(R.id.apply_button);
    }

    public void applyClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("sport", e1.getText().toString());
        resultIntent.putExtra("faculty", e2.getText().toString());
        resultIntent.putExtra("name", e3.getText().toString());
        resultIntent.putExtra("type", e4.getText().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
