package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.dataAccess.ConnectionDb;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    EditText Name, LastName, Email, Password;
    TextInputLayout inputLayoutName, inputLayoutLastName, inputLayoutEmail, inputLayoutPassword;
    private boolean autorizador =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = findViewById(R.id.editName);
        LastName= findViewById(R.id.editLastName);
        Email = findViewById(R.id.editEmail);
        Password = findViewById(R.id.editPassword);

        //inputsLayout

        inputLayoutName = findViewById(R.id.textInputLayoutName);
        inputLayoutLastName= findViewById(R.id.textInputLayoutLastName);
        inputLayoutEmail= findViewById(R.id.textInputLayoutEmail);
        inputLayoutPassword= findViewById(R.id.textInputLayoutPassword);

        //validation
        validateEmail();

    }
    public void onPause() {

        super.onPause();
        super.onRestart();
    }
    public void onBackPressed(Bundle savedInstanceState){
        validateEmail();
        Toast.makeText(this, "aquitoy", Toast.LENGTH_SHORT).show();
    }

    public void Register (View view){
        ConnectionDb con = new ConnectionDb(this);

        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();



        if (!Name.getText().toString().isEmpty() && !LastName.getText().toString().isEmpty() && !Email.getText().toString().isEmpty() && !Password.getText().toString().isEmpty() && autorizador==true){

            //boolean res= con.validateIfExistEmail(Email.getText().toString());


                values.put("name", Name.getText().toString());
                values.put("lastName", LastName.getText().toString());
                values.put("email", Email.getText().toString());
                values.put("password", Password.getText().toString());
                long noRegistro =db.insert("user", null, values);

                Toast.makeText(this, "Registro No "+noRegistro, Toast.LENGTH_SHORT).show();
                db.close();

                //abrir activity donde se enlistan los usuarios.
                Intent showListUsers= new Intent(this, ListUsers.class);
                startActivity(showListUsers);

        }else{
            Toast.makeText(this, "hay campos vacios ", Toast.LENGTH_SHORT).show();
        }
    }

    public void validateEmail(){
        final ConnectionDb con = new ConnectionDb(this);

        boolean res= con.validateIfExistEmail(Email.getText().toString());
        if(res==true){
            inputLayoutEmail.setError("This email alrrady exist");
            autorizador=false;
        }else{
            inputLayoutEmail.setErrorEnabled(false);
            autorizador=true;
        }

        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean res= con.validateIfExistEmail(s.toString());
                if(res == true){
                    inputLayoutEmail.setError("This email arrady exist");
                    autorizador= false;

                }else {
                    inputLayoutEmail.setErrorEnabled(false);
                    autorizador= true;
                }
            }
        });



    }


}
