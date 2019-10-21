package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.dataAccess.ConnectionDb;

public class Login extends AppCompatActivity {
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextEmail);
        password= findViewById(R.id.editTextPassword);
    }

    public void openListUsers(View view){
        ConnectionDb con = new ConnectionDb(this);
        boolean res = con.ValidateLogin(email.getText().toString(),password.getText().toString());
        if (res== true){
            Intent listUseres= new Intent(this,ListUsers.class);
            startActivity(listUseres);
        }else {
            Toast.makeText(this, "Error email or password incorrect", Toast.LENGTH_SHORT).show();
        }


        
    }
    public void openSignup(View view){
        Intent Register= new Intent(this, SignUp.class);
        startActivity(Register);
    }
}
