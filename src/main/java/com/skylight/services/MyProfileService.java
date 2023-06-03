package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.exceptions.UnauthorizedException;
import com.skylight.models.Flight;
import com.skylight.models.Ticket;
import com.skylight.models.User;
import com.skylight.repositories.UserRepository;
import com.skylight.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class MyProfileService {

   @Autowired
   private UserRepository userRepository;

   private final PasswordEncoder passwordEncoder;

   // Purpose is to encrypt password when updating profile
   public MyProfileService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
      this.passwordEncoder = passwordEncoder;
      this.userRepository = userRepository;
   }

   /**
    * Retrieves currently logged-in user's data. If there is no data (Example: After account deletion), an Unauthorized error message is
    * thrown.
    * @return Logged-in user's data
    */
   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   public static User getLoggedInUser() {
      MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      // Check that there is a logged-in user
      if (userDetails.getUser() == null || userDetails.getUsername().isEmpty() || userDetails.getUsername() == null) {
         // Return an error if the user is not found
         throw new UnauthorizedException("Unauthorized");
      }
      // Return the data for the logged-in user
      return userDetails.getUser();
   }

   // Functionality: Returns logged-in user’s account	(Public | Private)

   // Functionality: Edit user account	(Public | Private)

   public List<Ticket> getMyTickets() {
      Optional<User> myProfile = userRepository.findById(getLoggedInUser().getId());
      List<Ticket> myTickets = myProfile.get().getMyTicketsList();
      if(myTickets.isEmpty()) {
         throw new NotFoundException("No tickets found");
      }
      return myTickets;
   }

   //  Functionality: Returns a list of flight the user has submitted	(Public | Private)
}
