package com.apps.apene.tema3_ada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class QueryResult extends AppCompatActivity {

    protected TextView queryResult = null;
    protected ArrayList<String> data = new ArrayList<String>();

    // Método con el que recogemos los datos que nos envía QueriesLauncher
    protected void getData(){
        Bundle extras = getIntent().getExtras();
        data = extras.getStringArrayList("result");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);

        // Referencias
        queryResult = findViewById(R.id.tv_query_result);

        // Lanzamos el método getData()
        getData();

        // Pasamos al TextView los datos para mostrarlos
        queryResult.setText(data.toString());

    }
}
