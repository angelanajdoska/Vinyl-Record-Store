package com.example.vinylrecordstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button button;
    EditText email;
    EditText password;
    String user;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database_check(v);
            }
        });
        register = (TextView) findViewById(R.id.create);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void database_check(View view) {
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.pass);
        if(email.getText().toString().trim().length() == 0 || password.getText().toString().trim().length() == 0){
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_LONG).show();
        }
        else{
            MyDatabase myDatabase=new MyDatabase(LoginActivity.this);
            if(myDatabase.checkUser(email.getText().toString().trim()
                    , password.getText().toString().trim())) {
                user = email.getText().toString().trim();
                Intent intent = new Intent(this, BottomNavigationActivity.class);
                intent.putExtra("username", user);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "The username or password is incorrect", Toast.LENGTH_LONG).show();
            }
        }
    }
}
