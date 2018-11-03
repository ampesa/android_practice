package com.apps.apene.tema3_ada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Creamos los botones de la actividad principal
    protected Button mButtonAddStudent = null;
    protected Button mButtonAddLecturer = null;
    protected Button mButtonLaunchQueries = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias de los botones de la pantalla principal
        mButtonAddLecturer = findViewById(R.id.bt_add_lecturer);
        mButtonAddStudent = findViewById(R.id.bt_add_student);
        mButtonLaunchQueries = findViewById(R.id.bt_launch_queries);

        // Añadimos listeners a los botones
        mButtonAddStudent.setOnClickListener(this);
        mButtonAddLecturer.setOnClickListener(this);
        mButtonLaunchQueries.setOnClickListener(this);

    }

    // Menú de opciones que albergará el acceso a los Ajustes de Usuario
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Asignamos al menú el XML correspondiente
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Asignamos la funcionalidad a los items del menú
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_user_setting_edit :
                // Intent con el context y la nueva actividad
                Intent userSettings = new Intent(this, UserSettings.class);
                // Iniciamos la actividad
                startActivity(userSettings);
                return true;
            case R.id.menu_user_setting_show :
                // Intent con el context y la nueva actividad
                Intent userData = new Intent(this, UserData.class);
                // Iniciamos la actividad
                startActivity(userData);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add_student :
                // Intent con el context y la nueva actividad
                Intent addStudent = new Intent(this, AddStudent.class);
                // Iniciamos la actividad
                startActivity(addStudent);
                break;
            case R.id.bt_add_lecturer :
                // Intent con el context y la nueva actividad
                Intent addLecturer = new Intent(this, AddLecturer.class);
                // Iniciamos la actividad
                startActivity(addLecturer);
                break;
            case R.id.bt_launch_queries :
                // Intent con el context y la nueva actividad
                Intent launchQueries = new Intent(this, QueriesLauncher.class);
                // Iniciamos la actividad
                startActivity(launchQueries);
        }
    }
}
