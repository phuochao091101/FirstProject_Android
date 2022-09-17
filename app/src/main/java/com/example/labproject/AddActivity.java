package com.example.labproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText eName_user, eEmail_user, ePhone_user;
    Button btn_Cancel, btn_Add;
    Switch sActivity;
    String province="None";
    String department="IT";
    Boolean Agree=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        eName_user=findViewById(R.id.eName_user);
        eEmail_user=findViewById(R.id.eEmail_user);
        ePhone_user=findViewById(R.id.ePhone_user);
        sActivity=(Switch)findViewById(R.id.sActivity);
        btn_Cancel=findViewById(R.id.btn_cancel_user);
        btn_Add=findViewById(R.id.btn_update_user);
        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                AddActivity.this.finish();
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail(eEmail_user) && validateName(eName_user)&&validatePhone(ePhone_user)&&Agree!=false&&!province.equals("None")){
                    boolean checked = sActivity.isChecked();
                    String Name=eName_user.getText().toString();
                    String Email=eEmail_user.getText().toString();
                    String Phone=ePhone_user.getText().toString();
                    HomeActivity ob=new HomeActivity();

                    DatabaseHandler db=new DatabaseHandler(com.example.labproject.AddActivity.this);
                    db.insertDetails(Name,Email,department,province,Phone);

                    Toast.makeText(AddActivity.this, "Add success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddActivity.this,com.example.labproject.HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(AddActivity.this, "Add Fail", Toast.LENGTH_SHORT).show();
                }


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