package com.meliksah.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private Context context;
    private ArrayList<Item> recyclerItemValues;


    //STEP 1: Define Interface
    interface  UniversityRecyclerAdapterInterface{
        //STEP2: Which actions has to be implemented in activities, define corresponding methods for each
        void displayItem(Item sc);
    }

    //STEP3: Create a reference from interface type
    UniversityRecyclerAdapterInterface uniAdapterInterface;

    public MyRecyclerViewAdapter(Context context, ArrayList<Item> values){
        this.context = context;
        this.recyclerItemValues = values;
        //STEP 4: convert context to interface.
        // Here if context is MainActivity which implement SocialRecyclerAdapterInterfce, casting can be done
        //It means that in the MainActivity   displayItem method is implemented.
        uniAdapterInterface = (UniversityRecyclerAdapterInterface)context;
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recycler_item, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final Item sm = recyclerItemValues.get(i);

        myRecyclerViewItemHolder.tv.setText(sm.getName());

        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //STEP 5: Catch the event over the item, then call displayItem method
                uniAdapterInterface.displayItem(sm);
            }
        });


    }

    @Override
    public int getItemCount() {
        return recyclerItemValues.size();
    }

    class MyRecyclerViewItemHolder extends  RecyclerView.ViewHolder{
        TextView tv;
        ImageView img;
        ConstraintLayout parentLayout;
        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
