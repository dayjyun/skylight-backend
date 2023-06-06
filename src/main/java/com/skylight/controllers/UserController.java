package com.skylight.controllers;

import com.skylight.models.User;
import com.skylight.models.login.LoginRequest;
import com.skylight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {
   @Autowired
   private UserService userService;
   
   /**
    * Functionality: User creates account (Public)
    * Path: /api/auth/register
    * @param userObject is the data for the user being registered
    * @return the data for the newly registered user
    */
   @PostMapping(path = "/register")
   public ResponseEntity<User> createUser(@RequestBody @Valid User userObject) {
      return userService.createUser(userObject);
   }

   /**
    * Functionality: User logs into of account (Public)
    * Path: /api/auth/login
    * @param loginRequest user credentials (email, password)
    * @return JWT key
    */
   @PostMapping(path = "/login")
   public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
      return userService.loginUser(loginRequest);
   }
}
