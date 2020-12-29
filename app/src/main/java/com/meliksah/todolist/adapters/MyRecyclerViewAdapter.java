package com.meliksah.todolist.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.meliksah.todolist.R;
import com.meliksah.todolist.db.Commons;
import com.meliksah.todolist.models.ToDoItem;
import com.meliksah.todolist.models.ToDoList;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    public interface MyRecyclerViewInterface{
        public void displayDialog(int position);
    }

    Context context;
    Button btnSave;
    Dialog dialog;
    ArrayList<ToDoItem> toDoItems;
    ToDoList toDoList;
    MyRecyclerViewInterface myRecyclerViewInterface;


    public MyRecyclerViewAdapter(Context context, ToDoList data) {
        this.context = context;
        toDoList = data;
        toDoItems = data.getToDoItems();
        myRecyclerViewInterface = (MyRecyclerViewInterface) context;
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //BIND DATA
       final ToDoItem item =toDoItems.get(position);
        holder.name.setText(item.getName());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setIsComplete(isChecked);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewInterface.displayDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(toDoItems == null){
            return 0;
        }
        return toDoItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        CheckBox checkBox;
        ConstraintLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.tvName);
            checkBox =  itemView.findViewById(R.id.checkBox);
            parentLayout = itemView.findViewById(R.id.itemLayout);

        }
    }

    private void makeAndShowDialogBox(String message,Drawable dw[],int pos) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        btnSave = dialog.findViewById(R.id.btnSave);
        dialog.show();
    }


}