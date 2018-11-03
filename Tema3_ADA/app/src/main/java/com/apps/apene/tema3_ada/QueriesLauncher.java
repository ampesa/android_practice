package com.apps.apene.tema3_ada;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.apps.apene.tema3_ada.FloridaDB.TABLE_STUDENTS;

public class QueriesLauncher extends AppCompatActivity implements View.OnClickListener{

    // Creamos los botones
    private Button mQueryAllStudents = null;
    private Button mQueryStudentsByGrade = null;
    private Button mQueryStudentsByCourse = null;
    private Button mQueryAllLecturers = null;
    private Button mQueryAll = null;

    // Creamos un adaptador
    private FloridaDBAdapter dbAdapter;
    // Creamos un context para pasar el contexto
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries_launcher);

        // Referencias
        mQueryAllStudents = findViewById(R.id.bt_query_all_students);
        mQueryStudentsByGrade = findViewById(R.id.bt_query_students_by_grade);
        mQueryStudentsByCourse = findViewById(R.id.bt_query_students_by_course);
        mQueryAllLecturers = findViewById(R.id.bt_query_all_lecturers);
        mQueryAll = findViewById(R.id.bt_query_all);
        dbAdapter = new FloridaDBAdapter(this);
        mContext = this.getApplicationContext();

        // Añadimos los listener a los botones
        mQueryAll.setOnClickListener(this);
        mQueryAllLecturers.setOnClickListener(this);
        mQueryStudentsByCourse.setOnClickListener(this);
        mQueryStudentsByGrade.setOnClickListener(this);
        mQueryAllStudents.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_query_all_students :
                dbAdapter.open();
                ArrayList<String> students = dbAdapter.getAllStudents();
                // Intent con el context y la nueva actividad
                Intent allStudents = new Intent(this, QueryResult.class);
                // Pasamos el ArrayList a la nueva actividad
                allStudents.putExtra("result", students);
                // Iniciamos la actividad
                startActivity(allStudents);

                break;
            case R.id.bt_query_students_by_grade :
                dbAdapter.open();
                ArrayList<String> studentsByGrade = dbAdapter.getStudentsByGrade();
                // Intent con el context y la nueva actividad
                Intent studentsGrade = new Intent(this, QueryResult.class);
                // Pasamos el ArrayList a la nueva actividad
                studentsGrade.putExtra("result", studentsByGrade);
                // Iniciamos la actividad
                startActivity(studentsGrade);
                break;
            case R.id.bt_query_students_by_course :
                dbAdapter.open();
                ArrayList<String> studentsByCourse = dbAdapter.getStudentsByCourse();
                // Intent con el context y la nueva actividad
                Intent studentsCourse = new Intent(this, QueryResult.class);
                // Pasamos el ArrayList a la nueva actividad
                studentsCourse.putExtra("result", studentsByCourse);
                // Iniciamos la actividad
                startActivity(studentsCourse);
                break;
            case R.id.bt_query_all_lecturers :
                dbAdapter.open();
                ArrayList<String> lecturers = dbAdapter.getAllLecturers();
                // Intent con el context y la nueva actividad
                Intent allLecturers = new Intent(this, QueryResult.class);
                // Pasamos el ArrayList a la nueva actividad
                allLecturers.putExtra("result", lecturers);
                // Iniciamos la actividad
                startActivity(allLecturers);
                break;
            case R.id.bt_query_all :
                dbAdapter.open();
                ArrayList<String> all = dbAdapter.getAll();
                // Intent con el context y la nueva actividad
                Intent allData = new Intent(this, QueryResult.class);
                // Pasamos el ArrayList a la nueva actividad
                allData.putExtra("result", all);
                // Iniciamos la actividad
                startActivity(allData);

        }


    }
}
