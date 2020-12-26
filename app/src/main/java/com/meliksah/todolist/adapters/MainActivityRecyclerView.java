package com.meliksah.todolist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.meliksah.todolist.R;
import com.meliksah.todolist.models.ToDoList;

import java.util.ArrayList;

public class MainActivityRecyclerView extends RecyclerView.Adapter<MainActivityRecyclerView.MyViewHolder> {
    ArrayList<ToDoList> items;
    Context context;

    public MainActivityRecyclerView(Context context,ArrayList<ToDoList> items){
        this.context=context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.second_recycler_item, parent, false);
        return new MainActivityRecyclerView.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ToDoList item = items.get(position);
        holder.toDoListName.setText(item.getName());
        holder.toDoListCreatedAt.setText(item.getCreatedAt());
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
            toDoListName =  itemView.findViewById(R.id.tvName);
            toDoListCreatedAt =  itemView.findViewById(R.id.checkBox);
            parentLayout = itemView.findViewById(R.id.toDoListLayout);

        }
    }
}
