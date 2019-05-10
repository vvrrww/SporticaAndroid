package com.example.sportica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class TournamentPlayerFormActivity extends AppCompatActivity {

    Spinner s1;
    Spinner s2;
    Spinner s3;
    EditText enter_name;
    Button apply_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_player_form);

        apply_button = this.findViewById(R.id.apply_button);
        s1 = this.findViewById(R.id.s1);
        s2 = this.findViewById(R.id.s2);
        s3 = this.findViewById(R.id.s3);
        enter_name = this.findViewById(R.id.enter_name);

        String[] sport_list = new String[]{"Bowling","Soccer","Badminton","Basketball","Tabletennis"};
        String[] faculty_list = new String[]{"Engineering","Liberal Art","Science","Dentist"};
        String[] type_list = new String[]{"Single","Double","Team"};

        ArrayList<String> aa = new ArrayList<String>();
        aa.addAll( Arrays.asList(sport_list) );
        ArrayAdapter aa1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sport_list);
        s1.setAdapter(aa1);

        ArrayList<String> bb = new ArrayList<String>();
        bb.addAll( Arrays.asList(faculty_list) );
        ArrayAdapter bb1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,faculty_list);
        s2.setAdapter(bb1);

        ArrayList<String> cc = new ArrayList<String>();
        cc.addAll( Arrays.asList(type_list) );
        ArrayAdapter cc1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type_list);
        s3.setAdapter(cc1);
    }

    public void applyClick(View v){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("sport", s1.getSelectedItem().toString());
        resultIntent.putExtra("faculty", s2.getSelectedItem().toString());
        resultIntent.putExtra("name", enter_name.getText().toString());
        resultIntent.putExtra("type", s3.getSelectedItem().toString());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
