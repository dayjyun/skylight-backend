package com.skylight.controllers;

import com.skylight.models.Flight;
import com.skylight.models.Ticket;
import com.skylight.models.User;
import com.skylight.services.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/myProfile")
public class MyProfileController {

   @Autowired
   private MyProfileService myProfileService;

   // Functionality: Returns logged-in user’s account (Private)
   // Path: /api/myProfile
   @GetMapping("")
   public User getMyProfile() {
      return MyProfileService.getLoggedInUser();
   }

   // Functionality: Edit user account (Private)
   // Path: /api/myProfile
   @GetMapping("")
   public User editMyProfile(@RequestBody User updateBody) {
      return myProfileService.updateMyProfile(updateBody);
   }

   // Functionality: Returns a list of flights the user booked	(Private)
   // Path: /api/myProfile/myTickets
   @GetMapping("/myTickets")
   public List<Ticket> getMyTickets() {
      return myProfileService.getMyTickets();
   }

   // Endpoint to become a pilot
   // Functionality: User becomes a pilot (Private)
   // Path: /api/myProfile/flyTheSkies
//   @PostMapping("/becomePilot")
//   public User becomePilot() {
//      return myProfileService.becomePilot();
//   }

   //  Functionality: Returns a list of flight the admin has submitted	(Public | Private)
   // Path: /api/myProfile/air
}
