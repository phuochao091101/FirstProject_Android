package com.example.labproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText eName_user, eEmail_user, ePhone_user;
    Button btn_cancel_user, btn_update_user;
    Switch sActivity;
    String province="None";
    String department="IT";
    Boolean Agree=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        eName_user=findViewById(R.id.eName_user);
        eEmail_user=findViewById(R.id.eEmail_user);
        ePhone_user=findViewById(R.id.ePhone_user);
        sActivity=(Switch)findViewById(R.id.sActivity);
        btn_update_user=findViewById(R.id.btn_update_user);
        btn_cancel_user=findViewById(R.id.btn_cancel_user);

        Intent intent=getIntent();
        int id= intent.getIntExtra("key",1);
        DatabaseHandler db=new DatabaseHandler(com.example.labproject.UpdateActivity.this);
        User user_result=db.searchById(id);
        eName_user.setText(user_result.getName());
        eEmail_user.setText(user_result.getEmail());
        ePhone_user.setText(user_result.getPhone());

        btn_cancel_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                UpdateActivity.this.finish();
            }
        });
        btn_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_result.setName(eName_user.getText().toString());
                user_result.setEmail(eEmail_user.getText().toString());
                user_result.setPhone(ePhone_user.getText().toString());
                System.out.println(user_result.getName());
                db.updateUser(user_result);
                Intent ob=new Intent(UpdateActivity.this,com.example.labproject.HomeActivity.class);
                startActivity(ob);
            }
        });
    }
    public Boolean validateEmail(EditText eEmail_user) {
        String val = eEmail_user.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.trim().isEmpty()) {
            eEmail_user.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            eEmail_user.setError("Invalid email address");
            return false;
        } else {
            eEmail_user.setError(null);
            return true;
        }
    }

    private Boolean validateName(EditText eName_user) {
        String val = eName_user.getText().toString();

        if (val.trim().isEmpty()) {
            eName_user.setError("Field cannot be empty");
            return false;
        }
        else {
            eName_user.setError(null);
            return true;
        }
    }
    private Boolean validatePhone(EditText ePhone_user) {
        String val = ePhone_user.getText().toString();

        if (val.trim().isEmpty()) {
            ePhone_user.setError("Field cannot be empty");
            return false;
        }
        else {
            ePhone_user.setError(null);
            return true;
        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rIT:
                if (checked)
                    department="IT";
                break;
            case R.id.rBusiness:
                if (checked)
                    department="Business";
                break;
        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cAgree:
                if (checked){
                    Agree=true;
                }else {
                    Agree=false;
                }
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        province=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}