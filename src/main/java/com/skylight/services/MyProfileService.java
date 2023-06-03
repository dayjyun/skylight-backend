package com.skylight.services;

import com.skylight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyProfileService {

   @Autowired
   private UserRepository userRepository;

   // Functionality: Returns logged-in user’s account	(Public | Private)

   // Functionality: Edit user account	(Public | Private)

   // Functionality: Returns a list of flights the user booked	(Public | Private)

   //  Functionality: Returns a list of flight the user has submitted	(Public | Private)
}
