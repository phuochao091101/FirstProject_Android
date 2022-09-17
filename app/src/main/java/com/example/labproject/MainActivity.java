package com.example.labproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText eEmail, ePassword;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eEmail=findViewById(R.id.eEmail);
        ePassword=findViewById(R.id.ePassword);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( validateEmail(eEmail) & validatePassword(ePassword)){
                    String getEmail=eEmail.getText().toString();
                    String getPassword=ePassword.getText().toString();
                    if(CheckLogin(getEmail,getPassword)){
                        Intent intent=new Intent(MainActivity.this, com.example.labproject.HomeActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

    }
    public Boolean validateEmail(EditText eEmail) {
        String val = eEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.trim().isEmpty()) {
            eEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            eEmail.setError("Invalid email address");
            return false;
        } else {
            eEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(EditText ePassword) {
        String val = ePassword.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.trim().isEmpty()) {
            ePassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            ePassword.setError("Password is too weak");
            return false;
        } else {
            ePassword.setError(null);
            return true;
        }
    }

    private Boolean CheckLogin(String getEmail, String getPassword){
        String Email="ht01646@gmail.com";
        String Password="Hao123@";
        if(getEmail.equals(Email)&&getPassword.equals(Password)){
            return true;
        }else {
            Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}