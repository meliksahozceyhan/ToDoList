package com.meliksah.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Dialog dialog;
    Button btnSave,btnCancel;
    String name="", note="";
    EditText etName, etNote;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void displayDialog(View view){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        Button btnClose = dialog.findViewById(R.id.btnSave);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.show();


    }

}