package com.example.shoppinglist.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c){
        context = c;
    }

    public DBManager Open() throws SQLException{
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }

    public void close(){
        dbHelper.close();
    }

    /**
     * Adding shopping list row
      * @param type
     * @param date
     */
    public void inserShoppingtList(String type, String date){

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TYPE, type);
        cv.put(DatabaseHelper.DATE, date);
        database.insert(DatabaseHelper.TABLE_NAME_LIST, null, cv);

    }

    /**
     * Getting the shopping list
     * @return
     */
    public Cursor getShoppingList(){

        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TYPE, DatabaseHelper.DATE };
        //Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_LIST, columns, null, null, null, null, null);

        Cursor cursor = database.rawQuery("SELECT * from "+DatabaseHelper.TABLE_NAME_LIST+" Order by "+DatabaseHelper._ID+" DESC", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
