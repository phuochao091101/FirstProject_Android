package com.example.labproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> adapter;
    Context context;
    Button btn_add, btn_viewall;
    public static ArrayList<User> users=new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DatabaseHandler db=new DatabaseHandler(com.example.labproject.HomeActivity.this);
        users= db.getListUser();
        listView=(ListView)findViewById(R.id.listView);
        btn_add=findViewById(R.id.btn_add);
        btn_viewall=findViewById(R.id.btn_viewall);
        context=this;
        UserListViewAdapter userListViewAdapter=new UserListViewAdapter(users);
        listView.setAdapter(userListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(HomeActivity.this,com.example.labproject.ItemActivity.class);
                User user=(User) adapterView.getItemAtPosition(i);
                intent.putExtra("key",user.getId());
                startActivity(intent);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, com.example.labproject.AddActivity.class);
                startActivity(intent);
            }
        });
        btn_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, com.example.labproject.ItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}