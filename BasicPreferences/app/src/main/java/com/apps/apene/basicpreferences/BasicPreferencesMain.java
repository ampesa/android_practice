package com.apps.apene.basicpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BasicPreferencesMain extends AppCompatActivity {

    protected Button savePref = null;
    protected Button getPref = null;
    protected SharedPreferences preferences = null;
    protected SharedPreferences.Editor editor = null;
    public static final String PREFS = "My preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_preferences_main);

        savePref = findViewById(R.id.bt_save_pref);
        getPref = findViewById(R.id.bt_get_pref);

        savePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creamos el objeto preferencias pasándole el Sring y el int (modo)
                preferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

                // Obtenemos un editor para modificar las preferencias
                editor = preferences.edit();

                // Guardamos nuevos valores
                editor.putBoolean("isTrue", true);
                editor.putFloat("lastFloat", (float) 1.5);
                editor.putInt("wholeNumber", 12);
                editor.putLong("aNumber", 2);
                editor.putString("textEntryValue", "message");

                // Guardamos los cambios
                editor.commit();
            }
        });

        getPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // recuperamos los valores guardados
                boolean isTrue = preferences.getBoolean("isTrue", false);
                String text = preferences.getString("textEntryValue", "");

                Toast.makeText(BasicPreferencesMain.this, "He recuperado "
                        + isTrue + " y " + text, Toast.LENGTH_LONG).show();

            }
        });

    }
}
