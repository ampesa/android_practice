package com.apps.apene.opsprefsmultisize2;

import android.preference.PreferenceFragment;
import android.os.Bundle;

public class MyPreferences extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Añadimos el archivo con las preferencias
        addPreferencesFromResource(R.xml.preferences);
    }
}
