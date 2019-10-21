package com.example.crud.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String Name, LastName, Email, Password;
    private int Id;

    public UserModel() {
    }

    public UserModel(String name, String lastName, String email, String password, int id) {
        this.Name = name;
        this.LastName = lastName;
        this.Email = email;
        this.Password = password;
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


}
