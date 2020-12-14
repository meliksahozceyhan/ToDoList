package com.meliksah.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class ItemDB {
    public static String TABLE_NAME="item";
    public static String FIELD_ID = "id";
    public static String FIELD_NAME = "name";
    public static String FIELD_NOTE = "note";


    public static String CREATE_TABLE_SQL="CREATE TABLE TABLE_NAME ( "+FIELD_ID+" INTEGER, "+FIELD_NAME+" TEXT, "+FIELD_NOTE+" TEXT, "
            +" NUMERIC, PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT))";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Item>  getAllCars(DatabaseHelper dbHelper){
        Item anItem;
        ArrayList<Item> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String note= cursor.getString(2);
            anItem = new Item(id, name, note);
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static ArrayList<Item> findCar(DatabaseHelper dbHelper, String key) {
        Item anItem;
        ArrayList<Item> data = new ArrayList<>();
        String where = FIELD_ID +" like '%"+key+"%'";

        Cursor cursor = dbHelper.getSomeRecords(TABLE_NAME, null, where);
        Log.d("DATABASE OPERATIONS",  where+", "+cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String note= cursor.getString(2);

            anItem = new Item(id, name, note);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static boolean insert(DatabaseHelper dbHelper, String id, String name, String note) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_ID, id);
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_NOTE, note);
        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper dbHelper, String id, String name,String note) {
        //ContentValues  allows to define key value pairs.
        //The key represents the table column identifier and the value represents the content for the table record in this column.
        //ContentVales can be used for insert and update operations over table

        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_NOTE, note);

        String where = FIELD_ID +" = "+id;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }

    public static boolean delete(DatabaseHelper dbHelper, String id){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = "+id;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }
}
