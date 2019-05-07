package com.example.sportica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "SSODemo";

    TextView sportica,myTextView;
    EditText sidEditText;
    EditText passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sportica = this.findViewById(R.id.sportica);
        sidEditText = this.findViewById(R.id.sidEditText);
        passwordEditText = this.findViewById(R.id.passwordEditText);
        loginButton = this.findViewById(R.id.loginButton);
        myTextView = this.findViewById(R.id.myTextView);
    }

    public void loginClick(View v){
        Intent intent = new Intent(this, NavActivity.class);
        startActivity(intent);
    }

    public void onSSOLogin(View v){
        Intent intent=new Intent(this,ChulaSSOActivity.class);
        startActivityForResult(intent,ChulaSSOActivity.LOGIN);

    }

}
