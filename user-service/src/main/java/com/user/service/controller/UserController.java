package com.user.service.controller;

import com.user.service.dto.LoginRequest;
import com.user.service.dto.LoginResponse;
import com.user.service.dto.UserEntityRequest;
import com.user.service.dto.UserEntityResponse;
import com.user.service.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

  private IUserService userService;

  private static Logger log = LoggerFactory.getLogger(UserController.class);

  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<UserEntityResponse> registerUser(
      @RequestBody UserEntityRequest userEntityRequest) {
    log.info("Registering user: " + userEntityRequest.toString());
    UserEntityResponse userEntityResponse = userService.createUser(userEntityRequest);
    return new ResponseEntity<>(userEntityResponse, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
    log.info(
        "Login User : "
            + loginRequest.getEmail());

    String token = userService.validateUserAndGetToken(loginRequest);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(token);
    log.info("Token Generated Successfully");
    return new ResponseEntity<>(loginResponse, HttpStatus.OK);
  }

  @GetMapping("/validate-token")
  public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String header) {

    if (header==null || !header.startsWith("Bearer ")) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
    }

    boolean checkToken = userService.validateToken(header.substring(7));
    if (checkToken) {
      log.info("Token Validated Successfully");
      return new ResponseEntity<>(HttpStatus.OK);
    }
    else {
      log.warn("Invalid token");
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("get-all")
  public ResponseEntity<List<UserEntityResponse>> getAllUsers() {
    List<UserEntityResponse> userEntityResponseList = userService.getAllUserEntities();
    log.info("Ger All Users List Successfully");
    return new ResponseEntity<>(userEntityResponseList, HttpStatus.OK);
  }
}
