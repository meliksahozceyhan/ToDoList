package com.meliksah.todolist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.meliksah.todolist.R;
import com.meliksah.todolist.adapters.MainActivityRecyclerView;
import com.meliksah.todolist.db.DatabaseHelper;
import com.meliksah.todolist.db.ToDoListDB;
import com.meliksah.todolist.models.ToDoList;
import com.meliksah.todolist.service.ToDoItemDatabaseIntentService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityRecyclerView.MainActivityRecyclerViewInterface{
    RecyclerView recyclerMain;
    FloatingActionButton floatingActionButton;
    ArrayList<ToDoList> toDoLists;
    DatabaseHelper dbHelper;
    Dialog dialog;

    @Override
    protected void onResume() {
        refreshList();
        super.onResume();
    }

    public void refreshList(){
        toDoLists =ToDoListDB.getAllItems(dbHelper);
        MainActivityRecyclerView mainActivityRecyclerView = new MainActivityRecyclerView(this,toDoLists);
        recyclerMain.setAdapter(mainActivityRecyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("INSERT_COMPLETE");
        intentFilter.addAction("UPDATE_COMPLETE");
        intentFilter.addAction("DELETE_COMPLETE");
        registerReceiver(serviceBroadcastReceiver,intentFilter);

        recyclerMain = findViewById(R.id.recyclermain);
        floatingActionButton = findViewById(R.id.mainFloatingActionButton);
        dbHelper = new DatabaseHelper(this);
        toDoLists = ToDoListDB.getAllItems(dbHelper);
        MainActivityRecyclerView mainActivityRecyclerView = new MainActivityRecyclerView(this,toDoLists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMain.setLayoutManager(layoutManager);
        recyclerMain.setAdapter(mainActivityRecyclerView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(null);
            }
        });

    }

    @Override
    public void startIntent(ToDoList item) {
        Intent intent = new Intent(this, SecondActivity.class);
        if(item == null){
            intent.putExtra("item",item);
        }
        else{
            intent.putExtra("item",item);
        }
        this.startActivity(intent);
    }

    @Override
    public void displayNoteDialog(final ToDoList item,  final int position) {
        dialog = new Dialog(this);
            dialog.setContentView(R.layout.note_dialog_update);
            Button saveButton,cancelButton,btnDelete;
            final EditText etToDoListNote = dialog.findViewById(R.id.etToDoListNote);
            saveButton = dialog.findViewById(R.id.saveButton);
            cancelButton = dialog.findViewById(R.id.cancelButton);
            btnDelete = dialog.findViewById(R.id.btnDelete);

            if(item.getNote() != ""){
                etToDoListNote.setText(item.getNote());
            }
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String noteToSave = etToDoListNote.getText().toString();
                    toDoLists.get(position).setNote(noteToSave);
                    Intent intent = new Intent(MainActivity.this,ToDoItemDatabaseIntentService.class);
                    Bundle b = new Bundle();
                    b.putParcelable("item",toDoLists.get(position));
                    b.putInt("type",1);
                    dialog.dismiss();
                }
            });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ToDoItemDatabaseIntentService.class);
                    Bundle b = new Bundle();
                    b.putParcelable("item",toDoLists.get(position));
                    b.putInt("type",2);
                    intent.putExtras(b);
                    startService(intent);
                    dialog.dismiss();
                }
            });
            dialog.show();
    }
    BroadcastReceiver serviceBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean res = intent.getBooleanExtra("result",false);
            String msg = "";
            if(action.equalsIgnoreCase("INSERT_COMPLETE")){
                msg = "Insert completed";
                if(!res){
                    msg = "Insert Failed";
                }
            }
            else if(action.equalsIgnoreCase("UPDATE_COMPLETE")){
                msg = "Update completed";
                if(!res){
                    msg="Update Failed";
                }
            }
            else{
                msg="Delete Completed";
                if(!res){
                    msg="Delete Failed";
                }
            }
            refreshList();
            displayToast(msg);
        }
    };
    void displayToast(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}