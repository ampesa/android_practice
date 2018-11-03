package com.apps.apene.tema3_ada;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class UserSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        // Mostramos el PreferenceFragment en la actividad
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPreferences())
                .commit();

        // Get user saved preferences
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        String name = sharedPreferences
                .getString("pref_name","NOVALUE");

        Toast.makeText(this, "Hello "+ name, Toast.LENGTH_LONG).show();


    }
}
