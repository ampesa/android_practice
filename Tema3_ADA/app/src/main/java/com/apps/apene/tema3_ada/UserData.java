package com.apps.apene.tema3_ada;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserData extends AppCompatActivity {

    // Creamos los objetos gráficos
    protected TextView mName = null;
    protected TextView mUserName = null;
    protected TextView mBirthDate = null;
    protected TextView mGender = null;
    protected TextView mLabelName = null;
    protected TextView mLabelUserName = null;
    protected TextView mLabelBirthDate = null;
    protected TextView mLabelGender = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        // Referenciamos los objetos gráficos
        mName = findViewById(R.id.tv_name);
        mUserName = findViewById(R.id.tv_user_name);
        mBirthDate = findViewById(R.id.tv_birth_date);
        mGender = findViewById(R.id.tv_gender);
        mLabelName = findViewById(R.id.tv_label_name);
        mLabelUserName = findViewById(R.id.tv_label_user_name);
        mLabelBirthDate = findViewById(R.id.tv_label_birth_date);
        mLabelGender = findViewById(R.id.tv_label_gender);

        // Obtenemos los datos de las prefernecias
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sharedPreferences.getString("pref_name","NOVALUE");
        String userName = sharedPreferences.getString("pref_user_name", "NOVALUE");
        String birthDate = sharedPreferences.getString("pref_birth_date", "NOVALUE");
        String gender = sharedPreferences.getString("pref_gender" , "1");

        // Pasamos los datos a los TextView
        mName.setText(name);
        mUserName.setText(userName);
        mBirthDate.setText(birthDate);
        if (gender.equals("1")){
            mGender.setText("Femenino");
        } else {
            mGender.setText("Masculino");
        }

    }
}
