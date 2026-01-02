package com.shopping_cart.app.controller;

import com.shopping_cart.app.http.request.LoginRequest;
import com.shopping_cart.app.http.request.UserEntityRequest;
import com.shopping_cart.app.http.response.UserEntityResponse;
import com.shopping_cart.app.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntityResponse> registerUser(@RequestBody UserEntityRequest userEntityRequest){
        log.info(userEntityRequest.getName());
        UserEntityResponse userEntityResponse=userService.createUser(userEntityRequest);
        return new ResponseEntity<>(userEntityResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        log.info("Login Username : "+loginRequest.getUsername()+" and password is: "+loginRequest.getPassword());
        boolean isValidUser = userService.validateUser(loginRequest);

        if(isValidUser) {
            return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Login Failed",HttpStatus.BAD_REQUEST);
        }

    }
}
