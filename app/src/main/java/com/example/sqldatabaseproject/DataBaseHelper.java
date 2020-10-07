package com.example.sqldatabaseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DataBaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME="data2.db";
        public static final String TABLE_NAME="Table2";
        public final static String COL3 = "PASSWORD";
        public final static String COL1 = "NAME";
        public final static String COL2 = "EMAIL";
        Context context;
    public DataBaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
        this.context=context;
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (NAME TEXT ,EMAIL TEXT,PASSWORD TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "Table is already present.", Toast.LENGTH_SHORT).show();
    }
    public boolean insertData(String name, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(COL1, name);
        contentValues.put(COL2, email);
        contentValues.put(COL3, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public Cursor getData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            Toast.makeText(context, "Data will be shown", Toast.LENGTH_SHORT).show();
            return cursor;
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            return null;
        }


    }
    }






