package com.example.shoppinglist.model;

public class UserModel {

    int id;
    String name;
    String email;
    String address;
    String phone;

    public UserModel() {
    }

    public UserModel(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getPhone() {
        return phone;
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
