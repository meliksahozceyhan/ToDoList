package com.meliksah.todolist.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;

import com.meliksah.todolist.db.DatabaseHelper;
import com.meliksah.todolist.db.ToDoListDB;
import com.meliksah.todolist.models.ToDoItem;
import com.meliksah.todolist.models.ToDoList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoItemDatabaseIntentService extends IntentService {




    public ToDoItemDatabaseIntentService() {
        super("ToDoItemDatabaseIntentService");
    }


    protected void onHandleIntent(Intent intent) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if(intent != null){
            Intent broadcastIntent = new Intent();
            Bundle b = intent.getExtras();
            ToDoList item = b.getParcelable("item");
            int processType = b.getInt("type",0);
            boolean result;
            if(processType == 1){
                if(item.getId() == 0){
                    String itemsStr = item.generateJSON();
                    Date date = new Date();
                    result = ToDoListDB.insert(dbHelper,item.getName(),item.getNote(),date.toString(),itemsStr);
                    broadcastIntent.putExtra("result",result);
                    broadcastIntent.setAction("INSERT_COMPLETE");
                }
                else{
                    String itemStr = item.generateJSON();
                    result = ToDoListDB.update(dbHelper,item.getId(),item.getName(),item.getNote(),itemStr);
                    broadcastIntent.putExtra("result",result);
                    broadcastIntent.setAction("UPDATE_COMPLETE");
                }

            }
            else if(processType == 2){
                result = ToDoListDB.delete(dbHelper,item.getId());
                broadcastIntent.putExtra("result",result);
                broadcastIntent.setAction("DELETE_COMPLETE");

            }
            else{
                broadcastIntent.setAction("ALL_FAILED");
            }

            sendBroadcast(broadcastIntent);
        }
    }

}