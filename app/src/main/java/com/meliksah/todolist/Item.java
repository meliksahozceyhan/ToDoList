package com.meliksah.todolist;

public class Item {
    private String name;
    private int id;
    private String note;

    public Item(int id,String name,String note) {
        this.name = name;
        this.id = id;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
