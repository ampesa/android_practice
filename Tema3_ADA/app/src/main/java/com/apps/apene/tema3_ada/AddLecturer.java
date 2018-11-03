package com.apps.apene.tema3_ada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLecturer extends AppCompatActivity {

    // Creamos los EditText para los datos del profesor
    protected EditText mLecturerName = null;
    protected EditText mLecturerAge = null;
    protected EditText mLecturerGrade = null;
    protected EditText mLecturerCourse = null;
    protected EditText mLecturerOfficeNumber = null;
    protected Button mButtonSaveLecturer = null;
    private FloridaDBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecturer);

        // Referencias
        mLecturerName = findViewById(R.id.et_lecturer_name);
        mLecturerAge = findViewById(R.id.et_lecturer_age);
        mLecturerGrade = findViewById(R.id.et_lecturer_grade);
        mLecturerCourse = findViewById(R.id.et_lecturer_course);
        mLecturerOfficeNumber = findViewById(R.id.et_lecturer_office_number);
        mButtonSaveLecturer = findViewById(R.id.bt_save_lecturer);
        // Referencia del adapter, pasa el contexto de la actividad
        dbAdapter = new FloridaDBAdapter(this);

        // Listener botón GUARDAR PROFESOR
        mButtonSaveLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardamos los datos en la base de datos
                // Abrimos la base de datos en modo escritura
                dbAdapter.open();
                // Insertamos el profesor
                dbAdapter.insertLecturer(mLecturerName.getText().toString(), mLecturerAge.getText().toString(),
                        mLecturerGrade.getText().toString(), mLecturerCourse.getText().toString(),
                        mLecturerOfficeNumber.getText().toString());
                // Limpiamos los edit Text
                mLecturerName.setText("");
                mLecturerAge.setText("");
                mLecturerGrade.setText("");
                mLecturerCourse.setText("");
                mLecturerOfficeNumber.setText("");
                // Notificamos que se ha guardado el profesor
                Toast.makeText(getApplicationContext(),"Profesor guardado", Toast.LENGTH_LONG).show();
            }
        });
    }
}
