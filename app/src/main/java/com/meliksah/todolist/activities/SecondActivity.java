package com.meliksah.todolist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.meliksah.todolist.R;
import com.meliksah.todolist.adapters.MyRecyclerViewAdapter;
import com.meliksah.todolist.models.ToDoItem;
import com.meliksah.todolist.models.ToDoList;
import com.meliksah.todolist.service.ToDoItemDatabaseIntentService;

import java.util.ArrayList;
import java.util.Date;

public class SecondActivity extends AppCompatActivity implements MyRecyclerViewAdapter.MyRecyclerViewInterface {

    RecyclerView toDoItemRecyclerView;
    TextView toDoItemHeader;
    ToDoList listItem;
    FloatingActionButton refreshFloatingActionButton, secondFloatingActionButton;
    Dialog dialog, createDialog;
    MyRecyclerViewAdapter adapter;
    Button btnSaveNClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toDoItemHeader = findViewById(R.id.toDoListHeader);
        toDoItemRecyclerView = findViewById(R.id.toDoItemRecyclerView);
        refreshFloatingActionButton = findViewById(R.id.refreshFloatingActionButton);
        secondFloatingActionButton = findViewById(R.id.secondFloatingActionButton);
        btnSaveNClose = findViewById(R.id.saveNCloseButton);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        toDoItemRecyclerView.setLayoutManager(linearLayoutManager);
        final Intent intent = getIntent();
        listItem = intent.getParcelableExtra("item");

        if(listItem == null){
            listItem = new ToDoList();
            listItem.setToDoItems(new ArrayList<ToDoItem>());
        }
        else{
            toDoItemHeader.setText(listItem.getName());
            if(listItem.getToDoItems() == null){
                listItem.setToDoItems(new ArrayList<ToDoItem>());

            }
        }
        refreshFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(ToDoItem toDoItem: listItem.getToDoItems()){
                    toDoItem.setIsComplete(false);
                }
            }
        });

        secondFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog(-1);
            }
        });
        btnSaveNClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listItem.getId() != null){
                    Intent intent1 = new Intent(SecondActivity.this,ToDoItemDatabaseIntentService.class);
                    Bundle b1 = new Bundle();
                    b1.putParcelable("item",listItem);
                    b1.putInt("type",1);
                    intent1.putExtras(b1);
                    startService(intent1);
                    finish();
                }
                else   {
                    displayCreateDialog();
                }
            }
        });
        displayProducts();
    }
    public void displayProducts(){
        adapter = new MyRecyclerViewAdapter(this,listItem);
        toDoItemRecyclerView.setAdapter(adapter);
    }

    @Override
    public void displayDialog(final int position) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        Button saveButton,closeButton,deleteButton;
        final EditText etName;
        TextView tvName;
        saveButton = dialog.findViewById(R.id.btnSave);
        closeButton = dialog.findViewById(R.id.closeBtnDialog);
        deleteButton = dialog.findViewById(R.id.deleteBtn);
        etName = dialog.findViewById(R.id.etname);
        tvName = dialog.findViewById(R.id.tvName);
        if(position !=-1){
            tvName.setText(listItem.getToDoItems().get(position).getName());
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == -1){
                    String name = etName.getText().toString();
                    ToDoItem newItem = new ToDoItem(name,false);
                    listItem.getToDoItems().add(newItem);
                }
                else{
                    listItem.getToDoItems().get(position).setName(etName.getText().toString());
                }
                displayProducts();
                dialog.dismiss();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItem.getToDoItems().remove(position);
                displayProducts();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void displayCreateDialog(){
        createDialog = new Dialog(this);
        createDialog.setContentView(R.layout.new_todo_name_dialog);
        Button btnSave,btnClose;
        final EditText etToDoItem;
        btnSave = createDialog.findViewById(R.id.dialogSaveBtn);
        btnClose = createDialog.findViewById(R.id.dialogCloseBtn);
        etToDoItem = createDialog.findViewById(R.id.etItemName);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItem.setName(etToDoItem.getText().toString());

                Intent intent = new Intent(SecondActivity.this, ToDoItemDatabaseIntentService.class);
                listItem.setId(0);
                listItem.setCreatedAt((new Date()).toString());
                listItem.setNote("");
                Bundle b = new Bundle();
                b.putInt("type",1);
                b.putParcelable("item",listItem);
                intent.putExtras(b);
                startService(intent);
                createDialog.dismiss();
                finish();

            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog.dismiss();
                Toast.makeText(SecondActivity.this,"Please Enter a Name For the To Do List Item",Toast.LENGTH_SHORT).show();
            }
        });
        createDialog.show();
    }
}