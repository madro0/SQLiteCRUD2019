package com.example.crud.dataAccess;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crud.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ConnectionDb extends SQLiteOpenHelper {
    public ConnectionDb(@Nullable Context context) {
        super(context, "gymdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase gymdb) {
        gymdb.execSQL("Create Table user ("+"Id integer primary key autoincrement  not null,"+
                        "name text not null,"+
                        "lastname text not null,"+
                        "email text not null,"+
                        "password text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<UserModel> showListUsers(){

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM user", null);

        List<UserModel>  listUsers = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                listUsers.add(new UserModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(0)));
            }while (cursor.moveToNext());
        }
        return listUsers;
    }
    public boolean validateIfExistEmail(String email){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email= '"+ email+ "'Limit 1", null);
        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public boolean ValidateLogin(String email, String password){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email= '"+ email+ "' and password= '"+password+"'Limit 1", null);

        //Cursor cursor = db.rawQuery("Select * from user where email= '"+ email+ "'Limit 1", null);

        if(cursor.moveToFirst()){
            return  true;
        }else{
            return false;
        }
    }

    public boolean deleteUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        int confirm = db.delete("user", "Id =" + id,null);
        db.close();
        if(confirm<0){
            return false;
        }else{
            return true;
        }

    }
    public UserModel consultUser(int id){

        SQLiteDatabase db = getReadableDatabase();

        UserModel model = null;
        Cursor cursor = db.rawQuery("Select * from user where Id= '"+ id+ "'Limit 1", null);


        if(cursor.moveToFirst()){
            model = new UserModel();
            model.setName(cursor.getString(1));
            model.setLastName(cursor.getString(2));
            model.setEmail(cursor.getString(3));
            model.setPassword(cursor.getString(4));

            model.setId(cursor.getInt(0));

            return model;


        }else{
            return null;
        }


    }
    public boolean updateUser(UserModel model){

        ContentValues values= new ContentValues();
        values.put("name",model.getName());
        values.put("lastname",model.getLastName());
        values.put("email",model.getEmail());
        values.put("password",model.getPassword());

        SQLiteDatabase db = getWritableDatabase();




        long confirm= db.update("user",values,"Id ="+model.getId(),null);
        db.close();
        if(confirm>0){
            return true;
        }else return false;


    }
}
