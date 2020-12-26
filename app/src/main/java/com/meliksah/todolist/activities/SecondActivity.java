package com.meliksah.todolist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.meliksah.todolist.R;

public class SecondActivity extends AppCompatActivity {

    RecyclerView toDoItemRecyclerView;
    TextView toDoItemHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toDoItemHeader = findViewById(R.id.toDoItemTextView);
        toDoItemRecyclerView = findViewById(R.id.toDoItemRecyclerView);
    }
}