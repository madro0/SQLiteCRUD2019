package com.example.crud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.controller.RecyclerViewListUsers;
import com.example.crud.dataAccess.ConnectionDb;
import com.example.crud.model.UserModel;

import java.util.ArrayList;
import java.util.List;


public class ListUsers extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private RecyclerViewListUsers adapadorUser;
    private CardView car;

    private Menu menu;
    private androidx.appcompat.widget.Toolbar toolbar;

    private List<UserModel> listUser= new ArrayList<>();

    private List<UserModel> user= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);





        inflateRecyvclerView();

        //registerForContextMenu(car);
        toolbar=  findViewById(R.id.toolbarSearch);


        setSupportActionBar(toolbar);

    }
    public void inflateRecyvclerView(){
        recyclerViewUser= (RecyclerView)findViewById(R.id.recyclerView);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        ConnectionDb db = new ConnectionDb(getApplicationContext());

        //adapadorUser = new RecyclerViewListUsers(db.showListUsers());
        listUser= db.showListUsers();
        adapadorUser=new RecyclerViewListUsers(listUser);
        recyclerViewUser.setAdapter(adapadorUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);

        final MenuItem search = menu.findItem(R.id.searchUseres);
        final SearchView item= (SearchView) MenuItemCompat.getActionView(search);

        item.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                int NoRegister= filtrer(newText);
                Toast.makeText(ListUsers.this, NoRegister+" Registros encontrados.", Toast.LENGTH_SHORT).show();
                return true;


            }
        });

        return super.onCreateOptionsMenu(menu);
    }
//cod para llenar a mano una lista
    /* public List<UserModel> obtenerUsuarios(){
        List<UserModel> user = new ArrayList<>();
        user.add(new UserModel("Daniel","Madroñero","daanii2013@gmail.com","123",1));
        user.add(new UserModel("Esteban","Muñoz","danniesteban1200@hotmail.com","123",1));
        return user;
    }*/


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switchecase(item);
        return super.onContextItemSelected(item);
    }

    public boolean switchecase(MenuItem item){
        switch (item.getItemId()){
            case 0:
                TextView text= findViewById(R.id.textId);

                openUpdate(item.getGroupId());


                return  true;
            case 1:

                dele(item.getGroupId());
                inflateRecyvclerView();
                Toast.makeText(this, "you has delete a user", Toast.LENGTH_SHORT).show();
                return  true;
            
            default:
                return super.onContextItemSelected(item);
        }
    }
    private int filtrer (String s){
        ConnectionDb db = new ConnectionDb(getApplicationContext());

        List<UserModel> filtrer = new ArrayList<>();
        int count=0;
        for (UserModel user: listUser ){
            if(user.getName().toLowerCase().contains(s.toLowerCase()) || user.getLastName().toLowerCase().contains(s.toLowerCase())||
                    user.getEmail().toLowerCase().contains(s.toLowerCase())){
                filtrer.add(user);
            count++;
            }
        }
        db.close();

        adapadorUser=new RecyclerViewListUsers(filtrer);
        recyclerViewUser.setAdapter(adapadorUser);

        return count;
    }

    public void dele(int id){
        ConnectionDb db = new  ConnectionDb(getApplicationContext());
        if(db.deleteUser(id)==false)
            Toast.makeText(this, "Error no eliminado", Toast.LENGTH_SHORT).show();
    }

    public void bringPersonalDataUsar(int id){

        ConnectionDb con = new ConnectionDb(this);

        SQLiteDatabase db = con.getReadableDatabase();

        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("Select * from user where Id=" + id + "  Limit 1", null);
        if (cursor.moveToFirst()){
            Intent abrirUpdate= new Intent(this, Update.class);
            new Update().id=id;
            startActivity(abrirUpdate);
        }else{
            Toast.makeText(this, "paila ", Toast.LENGTH_SHORT).show();
        }
    }

    /*public void bringUser(int id){


        ConnectionDb db = new ConnectionDb(getApplicationContext());


        user= db.showListUsers();
        List<UserModel> user2= new ArrayList<>();
        for (UserModel user: user ){{
            user2.add(new UserModel(user.getString(1),"","","",1));
        }}
    }*/

    public UserModel traer(int id){
        ConnectionDb con= new ConnectionDb(this);
        return  con.consultUser(id);

    }
    public void openUpdate(int id){
        Intent Register= new Intent(this, Update.class);
        Register.putExtra("idUser",id);
        startActivity(Register);
    }
}
