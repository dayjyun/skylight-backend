package com.skylight.services;

import com.skylight.exceptions.BadRequestException;
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

import javax.swing.text.html.Option;
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
    * getLoggedInUser retrieves currently logged-in user's data.
    * @throws UnauthorizedException is thrown if there is no user data
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

   /**
    * updateMyProfile updates the logged-in user's data
    * @param updateBody is the updated information for the logged-in user
    * @return updated user data
    */
   public User updateMyProfile(User updateBody) {
      // Create an optional of the logged-in user
      Optional<User> myProfile = userRepository.findById(getLoggedInUser().getId());
      // Check there is data for the logged-in user
      if (myProfile.isPresent()) {
         // check that the email does not already in the database, and it's not the same as current email
         if (userRepository.existsByEmail(updateBody.getEmail()) && !myProfile.get().getEmail().equals(updateBody.getEmail())) {
            throw new BadRequestException("Email already in use");
         }
         // Check that the password field is not empty when updating the password
         if (updateBody.getPassword() != null && !updateBody.getPassword().isEmpty()) {
            myProfile.get().setPassword(passwordEncoder.encode(updateBody.getPassword()));
         }
         // Check that the name field is not empty when updating the name
         if (updateBody.getName() != null && !updateBody.getName().isEmpty()) {
            myProfile.get().setName(updateBody.getName());
         }
         // Check that the email field is not empty when updating the email
         if (updateBody.getEmail() != null && !updateBody.getEmail().isEmpty()) {
            myProfile.get().setEmail(updateBody.getEmail());
         }
         // Save the updated data for the logged-in user
         return userRepository.save(myProfile.get());
      } else {
         // Return null if there is no user data
         return null;
      }
   }

   /**
    * getMyTickets retrieves a list of tickets the user has booked
    * @throws NotFoundException if the user has not booked any tickets
    * @return List of tickets the user has booked
    */
   public List<Ticket> getMyTickets() {
      // Create an optional of the logged-in user
      Optional<User> myProfile = userRepository.findById(getLoggedInUser().getId());
      // Check there is data for the logged-in user
      List<Ticket> myTickets = myProfile.get().getMyTicketsList();
      // Check that the user has booked any tickets
      if(myTickets.isEmpty()) {
         // Return an error if the user has not booked any tickets
         throw new NotFoundException("No tickets found");
      }
      // Return the list of tickets the user has booked
      return myTickets;
   }

   /**
    * flyTheSkies upgrades the logged-in user to an admin role
    * @return updated user data
    */
   public User flyTheSkies() {
      // Create an optional of the logged-in user
      Optional<User> loggedInUser = userRepository.findById(getLoggedInUser().getId());
      // Check there is data for the logged-in user
      if (loggedInUser.isPresent()) {
         // set the user as an admin
         loggedInUser.get().setAdmin(true);
         // Save the updated use
         return loggedInUser.get();
      }
      // Return null if there is no user data
      return null;
   }

   /**
    * getScheduledFlights retrieves a list of flights the user has scheduled
    * @throws NotFoundException if the user has not scheduled any flights
    * @return List of flights the user has scheduled
    */
   public List<Flight> getScheduledFlights() {
      // Create an optional of the logged-in user
      Optional<User> loggedInUser = userRepository.findById(getLoggedInUser().getId());
      // Check there is data for the logged-in user
      if (loggedInUser.isPresent()) {
         // Create a  list of flights the user has scheduled
         List<Flight> flights = loggedInUser.get().getMyFlightsList();
         // Check that the user has scheduled any flights
         if(flights.size() > 0) {
            // Return the list of flights the user has scheduled
            return flights;
         }
         // Return an error if the user has not scheduled any flights
         throw new NotFoundException("No flights found");
      }
      // Return null if there is no user data
      return null;
   }
}
