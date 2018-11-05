package com.apps.apene.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    protected EditText mLoginEmail = null;
    protected EditText mLoginPass = null;
    protected Button mButtonSignIn = null;
    protected TextView mForgotten = null;
    protected TextView mSignUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginEmail = findViewById(R.id.et_login_email);
        mLoginPass = findViewById(R.id.et_login_pass);
        mButtonSignIn = findViewById(R.id.bt_login_sign_in);
        mForgotten = findViewById(R.id.tv_login_forgotten);
        mSignUp = findViewById(R.id.tv_login_sign_up);

        mButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent main = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(main);
            }
        });

        mForgotten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(getApplicationContext(), SignUp.class);
                startActivity(signUp);
            }
        });

    }
}
