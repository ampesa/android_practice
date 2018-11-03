package com.apps.apene.tema3_ada;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class FloridaDB_Helper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Florida.db";


        public FloridaDB_Helper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(FloridaDB.SQL_CREATE_TABLE_STUDENTS);
            db.execSQL(FloridaDB.SQL_CREATE_TABLE_LECTURERS);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(FloridaDB.SQL_DELETE_TABLE_STUDENTS);
            db.execSQL(FloridaDB.SQL_DELETE_TABLE_LECTURERS);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

}
