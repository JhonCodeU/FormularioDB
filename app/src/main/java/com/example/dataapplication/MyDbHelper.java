package com.example.dataapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASES_NAME = "Areas.db";

    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASES_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE area(id INTEGER PRIMARY KEY, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists area");
        onCreate(db);
    }

    public void insertArea(Area area, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("id", area.getId());
        values.put("name", area.getName());
        db.insert("area", null, values);
    }

    public ArrayList<Area> selectArea(SQLiteDatabase db){
        ArrayList<Area> areas = new ArrayList<Area>();
        Cursor row = db.rawQuery("Select * from area", null);
        if (row.moveToFirst()){
            do {
                Area area = new Area(row.getInt(0), row.getString(1));
                areas.add(area);
            }while (row.moveToNext());
        }

        return areas;
    }
}
