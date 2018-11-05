package com.apps.apene.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    protected TextView mProfileEmail = null;
    protected TextView mProfilePass = null;
    protected TextView mProfileName = null;
    protected TextView mProfileFamily = null;
    protected TextView mProfileCountry = null;
    protected TextView mProfileZip = null;
    protected Button mButtonEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfileEmail = findViewById(R.id.tv_profile_email);
        mProfilePass = findViewById(R.id.tv_profile_pass);
        mProfileName = findViewById(R.id.tv_profile_name);
        mProfileFamily = findViewById(R.id.tv_profile_family);
        mProfileCountry = findViewById(R.id.tv_profile_country);
        mProfileZip = findViewById(R.id.tv_profile_zip);
        mButtonEdit = findViewById(R.id.bt_profile_edit);

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
