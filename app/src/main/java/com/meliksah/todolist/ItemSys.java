package com.meliksah.todolist;

import java.util.ArrayList;
import java.util.Collections;

public class ItemSys {
    private static ArrayList<Item> items;
    Item item = new Item(1,".",".");
    public static Item addItem(int id, String name, String note){
        Item item = new Item(id,name,note);
        items.add(item);
        return(item);
    }

    public static void removeItem(int id){
        for(Item i:items){
            if(i.getId() == id)
                items.remove(i);
        }
    }
    public static ArrayList<Item> getSocialItems() {
        return items;
    }
}
