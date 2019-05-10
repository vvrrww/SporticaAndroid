package com.example.sportica;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class WelcomeActivity extends AppCompatActivity {
    TextView welcomeText;
    String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeText = this.findViewById(R.id.welcomeText);

        if(getIntent().hasExtra("com.example.sportica.json")){
            try{
                json = getIntent().getExtras().getString("com.example.sportica.json");
                JSONObject user = new JSONObject(json);
                welcomeText.setText("Welcome back!\n"+user.getString("firstname")+" "+user.getString("lastname"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent startIntent = new Intent(getApplicationContext(),NewsFragment.class);
                startIntent.putExtra("com.example.sportica.json",json);
                startActivity(startIntent);
            }
        }, 3000);
    }
}
