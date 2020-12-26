package com.meliksah.todolist;

import android.database.Cursor;
import java.util.ArrayList;

public class Commons {//*******************Kullanırız diye düşündüm gereksizse sileriz
    public static Item item;
    public static Cursor cursor;
    public static int currentItemIndex=0;
    public static ArrayList<Item> data;
    public static Item getItem() {
        return item;
    }
    public static void setItem(Item item) {
        Commons.item = item;
    }
    public static ArrayList<Item> getData() {
        return data;
    }
    public static void setData(ArrayList<Item> data) {
        Commons.data = data;
    }
    public static DatabaseHelper dbhelper;
    public static DatabaseHelper getDbhelper() { return dbhelper; }
    public static void setDbhelper(DatabaseHelper dbhelper) { Commons.dbhelper = dbhelper; }
}
