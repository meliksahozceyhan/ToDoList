package com.meliksah.todolist.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.meliksah.todolist.models.ToDoItem;
import com.meliksah.todolist.models.ToDoList;

import org.json.JSONArray;

import java.util.ArrayList;

public class ToDoListDB {
    public static String TABLE_NAME="todolist";
    public static String FIELD_ID = "id";
    public static String FIELD_NAME = "name";
    public static String FIELD_DATE = "created_at";
    public static String FIELD_ITEMS = "items";
    public static String FIELD_NOTE = "note";


    public static String CREATE_TABLE_SQL="CREATE TABLE "+ TABLE_NAME+" ("
            +FIELD_ID+" INTEGER NOT NULL, "
            +FIELD_NAME+" text,"
            +FIELD_NOTE+" text,"
            +FIELD_DATE+" text,"
            +FIELD_ITEMS+" text,"
            +"PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT));";

    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<ToDoList>  getAllItems(DatabaseHelper dbHelper){

        ArrayList<ToDoList> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name = cursor.getString(1);
            String note= cursor.getString(2);
            String createdAt = cursor.getString(3);
            String toDoItemsString = cursor.getString(4);
            ArrayList<ToDoItem> toDoItems=  Commons.parseJSONToDoItem(toDoItemsString);
            ToDoList anItem = new ToDoList(name, id, note,createdAt,toDoItems);
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }


    public static boolean insert(DatabaseHelper dbHelper, String name, String note,String createdAt,String items) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_NOTE, note);
        contentValues.put(FIELD_DATE, createdAt);
        contentValues.put(FIELD_ITEMS,items);
        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper dbHelper, Integer id, String name,String note,String items) {
        //ContentValues  allows to define key value pairs.
        //The key represents the table column identifier and the value represents the content for the table record in this column.
        //ContentVales can be used for insert and update operations over table

        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_NAME, name);
        contentValues.put(FIELD_NOTE, note);
        contentValues.put(FIELD_ITEMS,items);

        String where = FIELD_ID +" = "+id;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }

    public static boolean delete(DatabaseHelper dbHelper, Integer id){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = "+id;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }
}
