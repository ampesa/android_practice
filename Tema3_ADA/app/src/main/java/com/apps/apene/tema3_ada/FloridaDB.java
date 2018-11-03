package com.apps.apene.tema3_ada;

public class FloridaDB {

    // Tablas
    public static final String TABLE_STUDENTS = "alumnnos";
    public static final String TABLE_LECTURERS = "profesores";

    // Campos comunes
    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "Nombre";
    public static final String KEY_AGE = "Edad";
    public static final String KEY_GRADE = "Ciclo";
    public static final String KEY_COURSE = "Curso";

    // Campos tabla alumnos
    public static final String KEY_AVERAGE = "NotaMedia";

    // Campos tabla profesores
    public static final String KEY_OFFICE_NUMBER = "Despacho";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    // Creación de la tabla alumnos
    protected static final String SQL_CREATE_TABLE_STUDENTS =
            "CREATE TABLE " + FloridaDB.TABLE_STUDENTS + " (" +
                    FloridaDB.KEY_ID + " INTEGER PRYMARY KEY AUTOINCREMENT," +
                    FloridaDB.KEY_NAME + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_AGE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_GRADE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_COURSE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_AVERAGE + TEXT_TYPE + " )";

    // Creación de la tabla profesores
    protected static final String SQL_CREATE_TABLE_LECTURERS =
            "CREATE TABLE " + FloridaDB.TABLE_LECTURERS + " (" +
                    FloridaDB.KEY_ID + " INTEGER PRYMARY KEY AUTOINCREMENT," +
                    FloridaDB.KEY_NAME + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_AGE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_GRADE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_COURSE + TEXT_TYPE + COMMA_SEP +
                    FloridaDB.KEY_OFFICE_NUMBER + TEXT_TYPE + " )";

    protected static final String SQL_DELETE_TABLE_STUDENTS
            = "DROP TABLE IF EXISTS " + FloridaDB.TABLE_STUDENTS;

    protected static final String SQL_DELETE_TABLE_LECTURERS
            = "DROP TABLE IF EXISTS " + FloridaDB.TABLE_LECTURERS;




}
