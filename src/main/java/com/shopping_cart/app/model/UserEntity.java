package com.shopping_cart.app.model;

public class UserEntity {

    public Long Id;

    public String name;

    public String email;

    public String location;

    public String username;

    public String password;

    public long number;

    public UserEntity(String name, String email, String location, String username, String password, long number) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.username = username;
        this.password = password;
        this.number = number;
    }
}
