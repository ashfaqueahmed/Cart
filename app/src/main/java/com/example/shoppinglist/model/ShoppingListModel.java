package com.example.shoppinglist.model;

public class ShoppingListModel {

    private int id;
    private String type;
    private String date;

    public ShoppingListModel(int id, String type, String date) {
        this.id = id;
        this.type = type;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
