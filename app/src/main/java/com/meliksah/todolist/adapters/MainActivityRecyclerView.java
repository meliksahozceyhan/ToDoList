package com.meliksah.todolist.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.meliksah.todolist.R;
import com.meliksah.todolist.activities.SecondActivity;
import com.meliksah.todolist.models.ToDoList;

import java.util.ArrayList;

public class MainActivityRecyclerView extends RecyclerView.Adapter<MainActivityRecyclerView.MyViewHolder> {
    ArrayList<ToDoList> items;
    Context context;


    public interface MainActivityRecyclerViewInterface {
        void startIntent(ToDoList item);
        void displayNoteDialog(ToDoList item,int position);
    }
    public MainActivityRecyclerView(Context context,ArrayList<ToDoList> items){
        this.context=context;
        this.items = items;
        this.mainActivityRecyclerViewInterface = (MainActivityRecyclerViewInterface) context;
    }


    MainActivityRecyclerViewInterface mainActivityRecyclerViewInterface;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.second_recycler_item, parent, false);
        return new MainActivityRecyclerView.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ToDoList item = items.get(position);
        holder.toDoListName.setText(item.getName());
        holder.toDoListCreatedAt.setText(item.getCreatedAt());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityRecyclerViewInterface.startIntent(item);
            }
        });
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mainActivityRecyclerViewInterface.displayNoteDialog(item,position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView toDoListName;
        TextView toDoListCreatedAt;
        ConstraintLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            toDoListName =  itemView.findViewById(R.id.toDoListName);
            toDoListCreatedAt =  itemView.findViewById(R.id.toDoListCreatedAt);
            parentLayout = itemView.findViewById(R.id.toDoListLayout);

        }
    }

}
