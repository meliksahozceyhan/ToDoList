package com.meliksah.todolist.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meliksah.todolist.R;
import com.meliksah.todolist.db.Commons;
import com.meliksah.todolist.models.ToDoItem;
import com.meliksah.todolist.models.ToDoList;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    Context context;
    Button btnSave;
    Dialog dialog;
    ArrayList<ToDoItem> toDoItems;
    ToDoList toDoList;

    public MyRecyclerViewAdapter(Context context, ToDoList data) {
        this.context = context;
        toDoList = data;
        toDoItems = data.getToDoItems();
    }
    // Each object of the ViewHolder will be created here
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycleritem, parent, false);
        return new MyViewHolder(v);
    }

    // This method will be called to assign data to each row or cell

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //BIND DATA
        ToDoItem item = Commons.data.get(position);
        holder.name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return toDoItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        CheckBox checkBox;
        LinearLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            parentLayout = (LinearLayout)itemView.findViewById(R.id.itemLayout);

        }
    }

    private void makeAndShowDialogBox(String message,Drawable dw[],int pos) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        btnSave = dialog.findViewById(R.id.btnSave);
        dialog.show();
    }


}