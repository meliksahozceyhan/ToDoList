package com.meliksah.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder > {
    private Context context;
    private ArrayList<Item> recyclerItemValues;

    public static final int TYPE_ODD_ITEM = 1;
    public static final int TYPE_EVEN_ITEM=2;
    public RecyclerViewAdapter(Context context, ArrayList<Item> values){
        this.context = context;
        this.recyclerItemValues = values;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView;
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());
        //STEP4
        if(viewType == TYPE_EVEN_ITEM) {
            //STEP5
            itemView = inflator.inflate(R.layout.recycler_even_item, viewGroup, false);
            MyRecyclerViewEvenItemHolder mViewHolder = new MyRecyclerViewEvenItemHolder(itemView);
            return mViewHolder;

        }
        else { //if(viewType == TYPE_ODD_ITEM) odd item
            //STEP5
            itemView = inflator.inflate(R.layout.recycler_odd_item, viewGroup, false);
            MyRecyclerViewOddItemHolder mViewHolder = new MyRecyclerViewOddItemHolder(itemView);
            return mViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder myRecyclerViewItemHolder, int position) {
        final Item item = recyclerItemValues.get(position);
        //STEP6
        if(getItemViewType(position) == TYPE_EVEN_ITEM){
            //STEP7
            MyRecyclerViewEvenItemHolder itemView = (MyRecyclerViewEvenItemHolder) myRecyclerViewItemHolder;
            itemView.tvSocialName.setText(item.getName());
            itemView.imgSocial.setImageResource(item.getId());

            //STEP8
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)context).displayDialog(item);
                }
            });

        }
        else if(getItemViewType(position) == TYPE_ODD_ITEM){
            //STEP7
            MyRecyclerViewOddItemHolder itemView = (MyRecyclerViewOddItemHolder)myRecyclerViewItemHolder;
            itemView.tvItemName.setText(item.getName());
            itemView.cbSocial.setText("like");

            //STEP8
            itemView.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)context).displayDialog(item);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    //STEP1:
    @Override
    public int getItemViewType(int position) {
        Item item = recyclerItemValues.get(position);
        if(position %2 ==0)
            return TYPE_EVEN_ITEM;
        else
            return TYPE_ODD_ITEM;
    }

    //STEP2

    class MyRecyclerViewEvenItemHolder extends  RecyclerView.ViewHolder{
        TextView tvSocialName;
        ImageView imgSocial;
        ConstraintLayout parentLayout;
        public MyRecyclerViewEvenItemHolder(@NonNull View itemView) {
            super(itemView);
            tvSocialName = itemView.findViewById(R.id.tvOddItemName);
            parentLayout = itemView.findViewById(R.id.itemEvenConstraintLayout);
        }
    }
    //STEP2
    class MyRecyclerViewOddItemHolder extends  RecyclerView.ViewHolder{
        TextView tvItemName;
        CheckBox cbSocial;
        ConstraintLayout parentLayout;
        public MyRecyclerViewOddItemHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvOddItemName);
            parentLayout = itemView.findViewById(R.id.itemOddConstraintLayout);
        }
    }

}