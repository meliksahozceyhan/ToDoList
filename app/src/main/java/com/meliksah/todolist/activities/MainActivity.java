package com.meliksah.todolist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.meliksah.todolist.R;
import com.meliksah.todolist.db.DatabaseHelper;
import com.meliksah.todolist.db.ToDoListDB;
import com.meliksah.todolist.models.ToDoList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerMain;
    FloatingActionButton floatingActionButton;
    ArrayList<ToDoList> toDoLists;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerMain = findViewById(R.id.recyclermain);
        floatingActionButton = findViewById(R.id.mainFloatingActionButton);
        dbHelper = new DatabaseHelper(this);
        toDoLists = ToDoListDB.getAllItems(dbHelper);
    }
}