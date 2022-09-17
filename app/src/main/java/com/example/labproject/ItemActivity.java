package com.example.labproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ItemActivity extends AppCompatActivity {
    TextView tv_item;
    Button btn_cancel, btn_delete, btn_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        tv_item=findViewById(R.id.tv_Item);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_delete=findViewById(R.id.btn_delete);
        btn_update=findViewById(R.id.btn_update);
        Intent intent=getIntent();

        int id= intent.getIntExtra("key",1);
        DatabaseHandler db=new DatabaseHandler(com.example.labproject.ItemActivity.this);
        User user_result=db.searchById(id);
        tv_item.setText(user_result.toString());
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                ItemActivity.this.finish();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.removeUserById(id);
                Intent ob=new Intent(ItemActivity.this,com.example.labproject.HomeActivity.class);
                startActivity(ob);
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob=new Intent(ItemActivity.this,com.example.labproject.UpdateActivity.class);
                ob.putExtra("key",id);
                startActivity(ob);
            }
        });
    }
}