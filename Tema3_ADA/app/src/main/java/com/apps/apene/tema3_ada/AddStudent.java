package com.apps.apene.tema3_ada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    // Creamos los EditText para los datos del estudiante
    protected EditText mStudentName = null;
    protected EditText mStudentAge = null;
    protected EditText mStudentGrade = null;
    protected EditText mStudentCourse = null;
    protected EditText mStudentAvQualification = null;
    protected Button mButtonSaveStudent = null;
    private FloridaDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Referencias
        mStudentName = findViewById(R.id.et_student_name);
        mStudentAge = findViewById(R.id.et_student_age);
        mStudentGrade = findViewById(R.id.et_student_grade);
        mStudentCourse = findViewById(R.id.et_student_course);
        mStudentAvQualification = findViewById(R.id.et_student_av_qualification);
        mButtonSaveStudent = findViewById(R.id.bt_save_student);
        // referencia del adapter, pasa el contexto de la actividad
        dbAdapter = new FloridaDBAdapter(this);

        // Listener botón GUARDAR ESTUDIANTE
        mButtonSaveStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardamos los datos en la BBDD
                // Abrimos la bse de datos en modo escritura
                dbAdapter.open();
                // Insertamos el alumno
                dbAdapter.insertStudent(mStudentName.getText().toString(), mStudentAge.getText().toString(),
                        mStudentGrade.getText().toString(), mStudentCourse.getText().toString(),
                        mStudentAvQualification.getText().toString());
                // Limpiamos los edit Text
                mStudentName.setText("");
                mStudentAge.setText("");
                mStudentGrade.setText("");
                mStudentCourse.setText("");
                mStudentAvQualification.setText("");
                //Notificamos que se ha guardado el estudiante
                Toast.makeText(getApplicationContext(),"Estudiante guardado", Toast.LENGTH_LONG).show();
            }
        });

    }
}
