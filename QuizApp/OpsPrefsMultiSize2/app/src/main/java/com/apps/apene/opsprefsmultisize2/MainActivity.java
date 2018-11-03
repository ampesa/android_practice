package com.apps.apene.opsprefsmultisize2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // creamos el menú de opciones y lo "inflamos" con el xml main
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_ver_datos :
                // Intent con el context y la nueva actividad
                Intent userData = new Intent(this, UserData.class);
                // Iniciamos la actividad
                startActivity(userData);
                return true;
            case R.id.menu_ajustes :
                // Intent con el context y la nueva actividad
                Intent userSettings = new Intent(this, UserSettings.class);
                // Iniciamos la actividad
                startActivity(userSettings);
                return true;
            case R.id.menu_ayuda :
                Toast.makeText(this, "Amadeo Penella", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
}
