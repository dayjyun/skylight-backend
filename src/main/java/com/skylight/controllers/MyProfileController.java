package com.skylight.controllers;

import com.skylight.models.Flight;
import com.skylight.models.Ticket;
import com.skylight.models.User;
import com.skylight.services.MyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/myProfile")
public class MyProfileController {

   @Autowired
   private MyProfileService myProfileService;

   /**
    * Functionality: Returns logged-in user’s account (Private)
    * Path: /api/myProfile
    * @return Logged-in user's data
    */
   @GetMapping("")
   public User getLoggedInUser() {
      return MyProfileService.getLoggedInUser();
   }

   /**
    * Functionality: Edit user account (Private)
    * Path: /api/myProfile
    * @param updateBody is the updated information for the logged-in user
    * @return updated user data
    */
   @PutMapping("")
   public User updateMyProfile(@RequestBody @Valid User updateBody) {
      return myProfileService.updateMyProfile(updateBody);
   }

   /**
    * Functionality: Returns a list of flights the user booked	(Private)
    * Path: /api/myProfile/myFlights
    * @return List of tickets the user has booked
    */
   @GetMapping("/myTickets")
   public List<Ticket> getMyTickets() {
      return myProfileService.getMyTickets();
   }

   /**
    * Functionality: User submits request to become a pilot (Private)
    * Path: /api/myProfile/flyTheSkies
    * @return updated user data
    */
   @PutMapping("/flyTheSkies") public User flyTheSkies() {
      return myProfileService.flyTheSkies();
   }

   /**
    * Functionality: Returns a list of flight the admin has submitted	(Private)
    * Path: /api/myProfile/air
    * @return List of flights the user has scheduled
    */
   @GetMapping("/air")
   public List<Flight> getScheduledFlights() {
      return myProfileService.getScheduledFlights();
   }
}
