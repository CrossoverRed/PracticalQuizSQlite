package com.example.practicalquizsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "schools.db";
    private static final String TABLE_SCHOOL = "school";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUM_OF_STU = "num_of_stu";
    private static final String COLUMN_SCHOOL_NAME = "school_name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //ToDo
        String createTableSql = "CREATE TABLE " + TABLE_SCHOOL +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NUM_OF_STU + " INTEGER,"
                + COLUMN_SCHOOL_NAME + " TEXT)";

        db.execSQL(createTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL);
        //Create table(s) again
        onCreate(db);
    }

    public void insertSchool(String numOfStudent, String schoolName){
        //ToDo
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUM_OF_STU, numOfStudent);
        values.put(COLUMN_SCHOOL_NAME, schoolName);
        db.insert(TABLE_SCHOOL, null, values);
        db.close();
    }

    public ArrayList<School> getSchools() {
        //ToDO
        ArrayList<School> schools = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NUM_OF_STU, COLUMN_SCHOOL_NAME};
        Cursor cursor = db.query(TABLE_SCHOOL, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int num_of_stu = cursor.getInt(1);
                String school_name = cursor.getString(2);

                School obj = new School(id, num_of_stu, school_name);
                schools.add(obj);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return schools;
    }
}
