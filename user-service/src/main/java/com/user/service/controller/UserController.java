package com.user.service.controller;

import com.user.service.dto.LoginRequest;
import com.user.service.dto.UserEntityRequest;
import com.user.service.dto.UserEntityResponse;
import com.user.service.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  private IUserService userService;

  private static Logger log = LoggerFactory.getLogger(UserController.class);

  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<UserEntityResponse> registerUser(
      @RequestBody UserEntityRequest userEntityRequest) {
    log.info(userEntityRequest.getName());
    UserEntityResponse userEntityResponse = userService.createUser(userEntityRequest);
    return new ResponseEntity<>(userEntityResponse, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
    log.info(
        "Login Username : "
            + loginRequest.getUsername()
            + " and password is: "
            + loginRequest.getPassword());
    boolean isValidUser = userService.validateUser(loginRequest);

    if (isValidUser) {
      return new ResponseEntity<>("Login Successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
    }
  }
}
