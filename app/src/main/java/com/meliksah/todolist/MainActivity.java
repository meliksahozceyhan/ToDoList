package com.meliksah.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayDialog(Item itemSelected){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        TextView tvDialogName = dialog.findViewById(R.id.tvDialogItem);
        ImageView imgDialog = dialog.findViewById(R.id.imgDialogItem);
        Button btnClose = dialog.findViewById(R.id.btnClose);

        tvDialogName.setText(itemSelected.getName());
        imgDialog.setImageResource(itemSelected.getId());
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }
}