package com.shopping_cart.app.http.request;

import lombok.Getter;

@Getter
public class UserEntityRequest {

    public String name;

    public String email;

    public String location;

    public String username;

    public String password;

    public long number;
}
