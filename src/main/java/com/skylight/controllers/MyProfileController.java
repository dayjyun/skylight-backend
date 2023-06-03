package com.skylight.controllers;

import com.skylight.models.User;
import com.skylight.services.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myProfile")
public class MyProfileController {

   @Autowired
   private MyProfileService myProfileService;

   // Functionality: Returns logged-in user’s account	(Public | Private)
   // Path: /api/myProfile
   @GetMapping("")
   public User getMyProfile() {
      return myProfileService.getLoggedInUser();
   }

   // Functionality: Edit user account	(Public | Private)
   // Path: /api/myProfile

   // Functionality: Returns a list of flights the user booked	(Public | Private)
   // Path: /api/myProfile/myTickets

   //  Functionality: Returns a list of flight the admin has submitted	(Public | Private)
   // Path: /api/myProfile/air
}
