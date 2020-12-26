package com.meliksah.todolist;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    Context context;
    Button btnSave;
    Dialog dialog;

    public MyRecyclerViewAdapter(Context context, List<Item> data) {
        this.context = context;
        Commons.data=(ArrayList<Item>) data;
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
        final Item item = Commons.data.get(position);
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return Commons.data.size();
    }

    // How many items exist in the list


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

    public void refreshMyAdapterAfterDelete(int position){
        ArrayList<Item> nw = Commons.getData();
        DatabaseHelper tempdb = Commons.getDbhelper();
        ItemDB.delete(tempdb,""+nw.get(position).getId());

    }

}