package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.demo.model.Student;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IamDeveloper on 2/18/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "student";
    private static final String ID = "id";
    private static final String TABLE_USER = "student";
    private static final String COL_STUDENT_ID = "studentId";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_YEAR = "year";
    private static final String COL_ADDRESS = "address";
    private static final String COL_AGE = "age";
    Cursor cursor;


    private static final String CREATE_DATABASE =
            "CREATE TABLE " + TABLE_USER + "(" + ID + " INTEGER PRIMARY KEY,"
                    + COL_STUDENT_ID + " VARCHAR(100)," + COL_NAME + " VARCHAR(100),"
                    + COL_SURNAME + " VARCHAR(100)," + COL_YEAR + " VARCHAR(100)," +
                    COL_ADDRESS + " VARCHAR(100)," + COL_AGE + " VARCHAR(100)" +")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public Student searchStudent(String query){
        Student student = new Student();
        String select = "SELECT * FROM " + TABLE_USER + " WHERE " + COL_STUDENT_ID + " = " + query;

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            cursor = db.rawQuery(select,null);
            if(cursor.moveToFirst()){
                do{
                    student.setStudentId(cursor.getString(cursor.getColumnIndex(COL_STUDENT_ID)));
                    student.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                    student.setSurname(cursor.getString(cursor.getColumnIndex(COL_SURNAME)));
                    student.setYear(cursor.getString(cursor.getColumnIndex(COL_YEAR)));
                    student.setAddress(cursor.getString(cursor.getColumnIndex(COL_ADDRESS)));
                    student.setAge(cursor.getString(cursor.getColumnIndex(COL_AGE)));
                }while (cursor.moveToNext());
            }
        }catch (SQLiteException e) {
            Log.d("database error",e.toString());
        }

        db.close();

        return student;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
        Log.i(TAG, "CREATE DATABASE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        Log.i(TAG, "UPGRADE DATABASE");
        onCreate(db);
    }



    public void insertUser(String studentId, String name, String surname, String year, String address, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("studentId", studentId);
        values.put("name", name);
        values.put("surname", surname);
        values.put("year",year);
        values.put("address", address);
        values.put("age", age);

        Log.i("add student", values.toString());
        db.insert(TABLE_USER, null, values);
    }

    public void updateUser(String id, String name, String pass, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("pass", pass);
        values.put("email", email);

        db.update(TABLE_USER, values, ID + " = ?", new String[]{id});
        Log.i(TAG, "UPDATE DATABASE");
    }

    public void deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, ID + " = ?", new String[]{id});
        Log.i(TAG, "DELETE DATABASE");
    }
}