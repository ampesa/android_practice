package com.apps.apene.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    protected EditText mSignUpEmail = null;
    protected EditText mSignUpPass = null;
    protected EditText mSignUpName = null;
    protected EditText mSignUpFamily = null;
    protected EditText mSignUpCountry = null;
    protected EditText mSignUpZip = null;
    protected Button mButtonSignUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mSignUpEmail = findViewById(R.id.et_signup_email);
        mSignUpPass = findViewById(R.id.et_signup_pass);
        mSignUpName = findViewById(R.id.et_signup_name);
        mSignUpFamily = findViewById(R.id.et_signup_family);
        mSignUpCountry = findViewById(R.id.et_signup_country);
        mSignUpZip = findViewById(R.id.et_signup_zip);
        mButtonSignUp = findViewById(R.id.bt_sign_up);

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
