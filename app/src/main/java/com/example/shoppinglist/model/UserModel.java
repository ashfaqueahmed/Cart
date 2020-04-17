package com.example.shoppinglist.model;

public class UserModel {

    int id;
    String name;
    String email;
    String address;

    public UserModel() {
    }

    public UserModel(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
