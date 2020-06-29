
package com.example.kukuapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class SQL extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "Kukuapp.db";
    private static final String TABLE_NAME = "configurationdb";
    private static final String _ID = "_idcon";
    private static final String COLUMN_NAME_TITLE = "configuration";
    private static final String COLUMN_NAME_SUBTITLE = "configurationsub";
    private static final String TABLE_NAME2 = "missCountdb";
    private static final String _ID2 = "_idMissCou";
    private static final String COLUMN_NAME2_TITLE = "missCount";
    private static final String COLUMN_NAME2_SUBTITLE = "missCountsub";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_SUBTITLE + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + TABLE_NAME2 + " (" +
                    _ID2 + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME2_TITLE + " INTEGER," +
                    COLUMN_NAME2_SUBTITLE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + TABLE_NAME2;


    public SQL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
