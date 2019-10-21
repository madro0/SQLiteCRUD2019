package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    EditText name,lastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        validate();



    }
    public void validate(){
        name= findViewById(R.id.editTextName);
        lastName= findViewById(R.id.editTextLastName);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("madro")){
                    Toast.makeText(Register.this, "holis", Toast.LENGTH_SHORT).show();

                    TextInputLayout layout= findViewById(R.id.layoutInputTextName);
                    layout.setError("Seleccione otro usuario");
                }else{
                    TextInputLayout layout= findViewById(R.id.layoutInputTextName);
                    layout.setErrorEnabled(false);
                }
            }
        });
    lastName.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().equals("")){
                TextInputLayout inputLayout= findViewById(R.id.textInputLayoutLastName);
                inputLayout.setError("No es posible dejar en blanco este campo");

            }else{
                TextInputLayout inputLayout= findViewById(R.id.textInputLayoutLastName);
                inputLayout.setErrorEnabled(false);
            }
        }
    });
    }
}
