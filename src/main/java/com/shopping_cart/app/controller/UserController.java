package com.shopping_cart.app.controller;

import com.shopping_cart.app.http.request.LoginRequest;
import com.shopping_cart.app.http.request.UserEntityRequest;
import com.shopping_cart.app.http.response.UserEntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserController {

    @PostMapping("/signup")
    public ResponseEntity<UserEntityResponse> registerUser(@RequestBody UserEntityRequest userEntityRequest){
        log.info(userEntityRequest.getName());
        UserEntityResponse userEntityResponse=new UserEntityResponse();
        userEntityResponse.setId(1L);
        userEntityResponse.setName(userEntityRequest.getName());
        userEntityResponse.setEmail(userEntityRequest.getEmail());
        userEntityResponse.setUsername(userEntityRequest.getUsername());
        userEntityResponse.setLocation(userEntityRequest.getLocation());
        userEntityResponse.setNumber(userEntityRequest.getNumber());
        return new ResponseEntity<>(userEntityResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        log.info("Login Username : "+loginRequest.getUsername()+" and password is: "+loginRequest.getPassword());
        return new ResponseEntity<>("Login Successfully",HttpStatus.OK);
    }
}
