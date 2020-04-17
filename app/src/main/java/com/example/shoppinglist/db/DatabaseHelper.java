package com.example.shoppinglist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME_LIST = "SHOPPING_LIST";

    // Table columns
    public static final String _ID = "_id";
    public static final String LIST_ID = "list_id";
    public static final String TYPE = "type";
    public static final String DATE= "date";

    // Database Information
    static final String DB_NAME = "SHOPPING_LIST.DB";
    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_LIST_TABLE = "create table " + TABLE_NAME_LIST + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TYPE + " TEXT NOT NULL, " + DATE + " TEXT);";


    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LIST);
        onCreate(db);
    }
}
