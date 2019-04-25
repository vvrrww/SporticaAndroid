package com.example.sportica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText ed1;
    Button b1;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = this.findViewById(R.id.ed1);
        b1 = this.findViewById(R.id.b1);

        ref = FirebaseDatabase.getInstance().getReference();

    }

    public void myClick(View v){
        String name = ed1.getText().toString();
        ref.setValue(name);
    }
}