package com.apps.apene.tema3_ada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FloridaDBAdapter {

    // Metadatos de la Base de Datos
    private static final String DATABASE_NAME = "florida.db";
    private static final int DATABASE_VERSION = 1;

    // Tablas
    private static final String TABLE_STUDENTS = "alumnnos";
    private static final String TABLE_LECTURERS = "profesores";

    // Campos comunes
    //private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "Nombre";
    private static final String KEY_AGE = "Edad";
    private static final String KEY_GRADE = "Ciclo";
    private static final String KEY_COURSE = "Curso";

    // Campos tabla alumnos
    private static final String KEY_AVERAGE = "NotaMedia";

    // Campos tabla profesores
    private static final String KEY_OFFICE_NUMBER = "Despacho";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    // Creación de la tabla alumnos
    private static final String SQL_CREATE_TABLE_STUDENTS =
            "CREATE TABLE " + FloridaDB.TABLE_STUDENTS + " (_Id integer primary key autoincrement," +
                    FloridaDB.KEY_NAME + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_AGE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_GRADE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_COURSE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_AVERAGE + TEXT_TYPE + " )";

    // Creación de la tabla profesores
    private static final String SQL_CREATE_TABLE_LECTURERS =
            "CREATE TABLE " + FloridaDB.TABLE_LECTURERS + " (_Id integer primary key autoincrement," +
                    FloridaDB.KEY_NAME + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_AGE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_GRADE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_COURSE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_OFFICE_NUMBER + TEXT_TYPE + " )";

    private static final String SQL_DELETE_TABLE_STUDENTS
            = "DROP TABLE IF EXISTS " + FloridaDB.TABLE_STUDENTS;

    private static final String SQL_DELETE_TABLE_LECTURERS
            = "DROP TABLE IF EXISTS " + FloridaDB.TABLE_LECTURERS;

    // Contexto de la aplicación que usa la base de datos
    private final Context context;

    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private FloridaDB_Helper mDbHelper;

    // Instancia de la base de datos
    private SQLiteDatabase db;

    public FloridaDBAdapter (Context c){
        context = c;
        mDbHelper = new FloridaDB_Helper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){

        try{
            db = mDbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = mDbHelper.getReadableDatabase();
        }

    }

    public void insertStudent(String name, String age, String grade, String course, String av){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(KEY_NAME,name);
        newValues.put(KEY_AGE, age);
        newValues.put(KEY_GRADE, grade);
        newValues.put(KEY_COURSE, course);
        newValues.put(KEY_AVERAGE, av);
        db.insert(TABLE_STUDENTS,null,newValues);
    }

    public void insertLecturer(String name, String age, String grade, String course, String on){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(KEY_NAME,name);
        newValues.put(KEY_AGE, age);
        newValues.put(KEY_GRADE, grade);
        newValues.put(KEY_COURSE, course);
        newValues.put(KEY_OFFICE_NUMBER, on);
        db.insert(TABLE_LECTURERS,null,newValues);
    }

    public ArrayList<String> getAllStudents(){
        ArrayList<String> students = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(TABLE_STUDENTS,null,null,null,
                null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                students.add(cursor.getString(1)+" "+cursor.getString(2) + " " +
                cursor.getString(3) + " " + cursor.getString(4) + " " +
                cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return students;
    }

    public ArrayList<String> getStudentsByGrade(){
        ArrayList<String> students = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(TABLE_STUDENTS,null,null,null,
                null,null,KEY_GRADE);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                students.add(cursor.getString(1)+" "+cursor.getString(2) + " " +
                        cursor.getString(3) + " " + cursor.getString(4) + " " +
                        cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return students;
    }

    public ArrayList<String> getStudentsByCourse(){
        ArrayList<String> students = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(TABLE_STUDENTS,null,null,null,
                null,null,KEY_COURSE);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                students.add(cursor.getString(1)+" "+cursor.getString(2) + " " +
                        cursor.getString(3) + " " + cursor.getString(4) + " " +
                        cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return students;
    }

    public ArrayList<String> getAllLecturers(){
        ArrayList<String> lecturers = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(TABLE_LECTURERS,null,null,null,
                null,null,KEY_COURSE);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                lecturers.add(cursor.getString(1)+" "+cursor.getString(2) + " " +
                        cursor.getString(3) + " " + cursor.getString(4) + " " +
                        cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return lecturers;
    }

    public ArrayList<String> getAll(){
        ArrayList<String> all = new ArrayList<String>();
        //Recuperamos en un cursor la consulta realizada
        String query = "SELECT DISTINCT * FROM " + TABLE_STUDENTS + " JOIN " + TABLE_LECTURERS + ";";
        Cursor cursor = db.rawQuery(query, null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do{
                all.add(cursor.getString(1)+" "+cursor.getString(2) + " " +
                        cursor.getString(3) + " " + cursor.getString(4) + " " +
                        cursor.getString(5));
            }while (cursor.moveToNext());
        }
        return all;
    }

    private static class FloridaDB_Helper extends SQLiteOpenHelper {

        // Constructor
        public FloridaDB_Helper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        // Ejecuta las sentencias de creación
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE_STUDENTS);
            db.execSQL(SQL_CREATE_TABLE_LECTURERS);
        }

        // Ejecuta las sentencias de actualización
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_TABLE_STUDENTS);
            db.execSQL(SQL_DELETE_TABLE_LECTURERS);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }

}
