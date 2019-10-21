package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud.dataAccess.ConnectionDb;
import com.example.crud.model.UserModel;

public class Update extends AppCompatActivity {
    public EditText name,lastName, email,password;
    public int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name=findViewById(R.id.editName);
        lastName=findViewById(R.id.editLastName);
        email=findViewById(R.id.editEmail);
        password=findViewById(R.id.editPassword);


        //bringPerson(id);

        Bundle extras = getIntent().getExtras();
         id = extras.getInt("idUser");
        bringPerson();

    }
    public void bringPerson(){

        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        ConnectionDb con = new ConnectionDb(this);

        UserModel user = con.consultUser(id);


        //id.setText(cursor.getString(0));
        name.setText(user.getName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        password.setText(user.getPassword());

    }
    public void upDateDataUser(View view){

        Bundle extras = getIntent().getExtras();
        int idUser = extras.getInt("idUser");


        UserModel model=null;
        model= new UserModel();
        model.setName(name.getText().toString());
        model.setLastName(lastName.getText().toString());
        model.setEmail(email.getText().toString());
        model.setPassword(password.getText().toString());
        model.setId(idUser);

        ConnectionDb con = new ConnectionDb(this);

        con.updateUser(model);

        Intent Register= new Intent(this, ListUsers.class);
        Register.putExtra("idUser",id);
        startActivity(Register);



    }

}
