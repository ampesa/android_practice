package com.apps.apene.opsprefsmultisize2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class UserData extends AppCompatActivity {

    // definimos los elementos gráficos
    protected TextView deleteOld = null;
    protected TextView charLimit = null;
    protected TextView  mobileData = null;
    protected TextView  operator = null;
    protected TextView deleteOldData = null;
    protected TextView charLimitData = null;
    protected TextView  mobileDataData = null;
    protected TextView  operatorData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        // Mostramos la barra de actividad
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // referencias de los elementos gráficos
        deleteOld = findViewById(R.id.tv_userdata_delete_old);
        charLimit = findViewById(R.id.tv_userdata_char_limit);
        mobileData = findViewById(R.id.tv_userdata_mobile_data);
        operator = findViewById(R.id.tv_userdata_operator);
        deleteOldData = findViewById(R.id.tv_userdata_delete_old_data);
        charLimitData = findViewById(R.id.tv_userdata_char_limit_data);
        mobileDataData = findViewById(R.id.tv_userdata_mobile_data_data);
        operatorData = findViewById(R.id.tv_userdata_operator_data);

        // Obtenemos los datos de las preferencias
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean delete = sharedPreferences.getBoolean("pref_delete_old",true);
        String limitChar = sharedPreferences.getString("pref_char_limit", "NOVALUE");
        Boolean dataMobile  = sharedPreferences.getBoolean("pref_mobile_data", false);
        String mobileOperator = sharedPreferences.getString("pref_operator", "No ha seleccionado operador");

        // Pasamos los datos a los TextView
        deleteOldData.setText(delete.toString());
        charLimitData.setText(limitChar);
        mobileDataData.setText(dataMobile.toString());

        // Manejamos el resultado del ListPreference
        if (mobileOperator.equals("1")){
            operatorData.setText("Vodafone");
        } else if (mobileOperator.equals("2")){
            operatorData.setText("Movistar");
        } else if (mobileOperator.equals("3")){
            operatorData.setText("Orange");
        } else if (mobileOperator.equals("4")){
            operatorData.setText("Otro");
        } else {
            operatorData.setText(mobileOperator);
        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Al pulsar atrás, finalizamos la actividad y volvemos a main
        switch (item.getItemId()){
            case android.R.id.home :
                this.finish();
                return true;
            default:
                return true;
        }
    }
}
