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

@RestController
@RequestMapping("/api/auth")
public class UserController {
   @Autowired
   private UserService userService;

   // Functionality: User creates account (Public)
   // Path: /api/auth/register
   @PostMapping(path = "/register")
   public User createUser(@RequestBody User user) {
      return userService.createUser(user);
   }

   // Functionality: User logs into of account (Public)
   // Path: /api/auth/login
   @PostMapping(path = "/login")
   public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
      return userService.loginUser(loginRequest);
   }

   // Functionality: User logs out of account (Public)
   // Path: /api/auth/logout
}
