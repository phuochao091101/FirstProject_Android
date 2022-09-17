package com.example.labproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;

public class ItemsActivity extends AppCompatActivity {
    TextView tv_viewall;
    Button btn_cancelall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        btn_cancelall=findViewById(R.id.btn_cancelview);
        tv_viewall=findViewById(R.id.tv_viewall);
        DatabaseHandler db=new DatabaseHandler(com.example.labproject.ItemsActivity.this);

        String details=db.getDetails();
        tv_viewall.setText(details);

        btn_cancelall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                ItemsActivity.this.finish();
            }
        });
    }
}