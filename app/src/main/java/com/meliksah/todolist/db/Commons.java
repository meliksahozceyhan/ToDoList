package com.meliksah.todolist.db;

import android.database.Cursor;

import com.meliksah.todolist.models.ToDoItem;
import com.meliksah.todolist.models.ToDoList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Commons {//*******************Kullanırız diye düşündüm gereksizse sileriz
    public static ArrayList<ToDoList> toDoLists;

    public static ToDoList toDoListItem;

    public static Cursor cursor;

    public static int currentItemIndex=0;

    public static ArrayList<ToDoItem> data;

    public static ToDoList getItem() {
        return toDoListItem;
    }
    public static void setItem(ToDoList item) {
        Commons.toDoListItem = item;
    }

    public static ArrayList<ToDoItem> getData() {
        return data;
    }

    public static void setData(ArrayList<ToDoItem> data) {
        Commons.data = data;
    }

    public static DatabaseHelper dbhelper;

    public static DatabaseHelper getDbhelper() { return dbhelper; }

    public static void setDbhelper(DatabaseHelper dbhelper) { Commons.dbhelper = dbhelper; }

    public static ArrayList<ToDoItem> parseJSONToDoItem(String toDoItemListStr)  {
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(toDoItemListStr);
            for(int i = 0 ; i<jsonArray.length();i++){
                JSONObject toDoItemJson = jsonArray.getJSONObject(i);
                String name = toDoItemJson.getString("name");
                Boolean isComplete = toDoItemJson.getBoolean("isComplete");
                toDoItems.add(new ToDoItem(name,isComplete));
            }
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }
        return toDoItems;
    }
}
