package com.meliksah.todolist;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Item> recyclerItemValues;
    Dialog customDialog;


    DatabaseHelper dbHelper;
    public MyRecyclerViewAdapter(Context context, ArrayList<Item> values){
        this.context = context;
        this.recyclerItemValues = values;
        dbHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflater.inflate(R.layout.recycler_item, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final Item item = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.tvitemname.setText(item.getName());
        myRecyclerViewItemHolder.tvitemnote.setText(item.getNote()+" TL");


       /* myRecyclerViewItemHolder.parentLayout.setOnLongClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog(item.toString());
                displayDialog(item.toString());
            }
        });*/
    }
    public void refreshMyAdapterAfterDelete(int position){
        recyclerItemValues.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView tvitemname, tvitemnote;
        ImageView imgitemImage;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            tvitemname = itemView.findViewById(R.id.tvName);
            tvitemnote = itemView.findViewById(R.id.tvNote);
        }
    }


    public void displayDialog(final String msg, final int pos){

        final TextView tv;
        Button btnDelete,btnUpdate;

        customDialog = new Dialog(context);

        customDialog.setContentView(R.layout.dialog_add);
        tv =  customDialog.findViewById(R.id.textView);
        btnDelete = customDialog.findViewById(R.id.btnDelete);
        btnUpdate = customDialog.findViewById(R.id.btnUPDATE);
        tv.setText(msg+"");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshMyAdapterAfterDelete(pos);
                customDialog.dismiss();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                Bundle b = new Bundle();
                Item temp = recyclerItemValues.get(pos);
                b.putParcelable("items",temp);
                intent.putExtras(b);
                context.startActivity(intent);

            }
        });
        customDialog.show();
    }

}
