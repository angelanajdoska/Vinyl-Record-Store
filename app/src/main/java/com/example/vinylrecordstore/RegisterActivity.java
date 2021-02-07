package com.example.vinylrecordstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText name;
    EditText address;
    EditText city;
    EditText phone;
    EditText email;
    EditText password1;
    Button button;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_input(v);
            }
        });
    }

    public void database_input(View view){
        name=(EditText) findViewById(R.id.name);
        address=(EditText) findViewById(R.id.address);
        city=(EditText) findViewById(R.id.city);
        phone=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email1);
        password1=(EditText) findViewById(R.id.pass1);
        if (name.getText().toString().trim().length() == 0 || address.getText().toString().trim().length() == 0 ||
                city.getText().toString().trim().length() == 0 || phone.getText().toString().trim().length() == 0
                || email.getText().toString().trim().length() == 0 || password1.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
        } else {
            MyDatabase myDatabase = new MyDatabase(RegisterActivity.this);
            myDatabase.insertUserDetails(name.getText().toString().trim(), address.getText().toString().trim(),
                    city.getText().toString().trim(), phone.getText().toString().trim(),
                    email.getText().toString().trim(), password1.getText().toString().trim());
            Toast.makeText(this, "You have successfully registered", Toast.LENGTH_SHORT).show();
            user=email.getText().toString().trim();
           Intent intent = new Intent(this, BottomNavigationActivity.class);
           intent.putExtra("username", user);
           startActivity(intent);
        }
    }

}