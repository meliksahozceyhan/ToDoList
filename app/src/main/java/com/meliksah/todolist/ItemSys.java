package com.meliksah.todolist;

import java.util.ArrayList;
import java.util.Collections;

public class ItemSys {
    private static ArrayList<Item> Items;

    public static Item addItem(int id, String name, String note){
        Item item = new Item(id,name,note);
        Items.add(item);
        return(item);
    }
    public static ArrayList<Item> getSocialItems() {
        return Items;
    }
}
