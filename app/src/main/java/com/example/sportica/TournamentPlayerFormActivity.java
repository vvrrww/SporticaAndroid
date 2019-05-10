package com.example.sportica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
//    Spinner s1;
    Button apply_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_player_form);

        apply_button = this.findViewById(R.id.apply_button);
        e1 = this.findViewById(R.id.e1);
        e2 = this.findViewById(R.id.e2);
        e3 = this.findViewById(R.id.e3);
        e4 = this.findViewById(R.id.e4);
//        s1 = this.findViewById(R.id.s1);
//
//        Intent intent = getIntent();
//        ArrayAdapter<String> aa = new ArrayAdapter<>(this,s1,intent.getStringArrayListExtra("cars").toArray());
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
