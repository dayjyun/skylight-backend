package com.skylight.controllers;

import com.skylight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
   @Autowired
   private UserService userService;

   // Functionality: User creates account (Public)
   // Path: /api/auth/register

   // Functionality: User logs into of account (Public)
   // Path: /api/auth/login

   // Functionality: User logs out of account (Public)
   // Path: /api/auth/logout
}
