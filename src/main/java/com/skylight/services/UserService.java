package com.skylight.services;

import com.skylight.exceptions.AlreadyExistsException;
import com.skylight.exceptions.BadRequestException;
import com.skylight.exceptions.NotFoundException;
import com.skylight.models.User;
import com.skylight.models.login.LoginRequest;
import com.skylight.models.login.LoginResponse;
import com.skylight.repositories.UserRepository;
import com.skylight.security.JWTUtils;
import com.skylight.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserService {

   private UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private JWTUtils jwtUtils;
   private AuthenticationManager authenticationManager;
   private MyUserDetails myUserDetails;

   @Autowired
   public UserService(UserRepository userRepository,
                      @Lazy PasswordEncoder passwordEncoder,
                      JWTUtils jwtUtils,
                      @Lazy AuthenticationManager authenticationManager,
                      @Lazy MyUserDetails myUserDetails) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
      this.jwtUtils = jwtUtils;
      this.authenticationManager = authenticationManager;
      this.myUserDetails = myUserDetails;
   }

   /**
    * createUser registers a new user to the database. There are field checkers in place to make sure that the required sections that must
    * be filled out are not left empty. If the email address already exists in the database, the user data will not be added to the
    * database
    * @param userObject is the data for the user being registered
    * @return the data for the newly registered user
    */
   public ResponseEntity<User> createUser(User userObject) {
      // Check that the name field is not empty when updating the name
      if (Objects.equals(userObject.getName(), "") || userObject.getName() == null) {
         throw new BadRequestException("Name is required");
      }
      // Check that the email field is not empty when updating the email
      if (Objects.equals(userObject.getEmail(), "") || userObject.getEmail() == null) {
         throw new BadRequestException("Email is required");
      }
      // Check that the password field is not empty when updating the password
      if (Objects.equals(userObject.getPassword(), "") || userObject.getPassword() == null) {
         throw new BadRequestException("Password is required");
      }
      // Check the email does not exist in the database
      if (!userRepository.existsByEmail(userObject.getEmail())) {
         // Hash the password the user entered
         userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
         // Return the data for the newly created user
         return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userObject));
      } else {
         // Throw an error if the email already exists in the database
         throw new AlreadyExistsException("User with email address " + userObject.getEmail() + " already exists");
      }
   }

   public User findUserByEmailAddress(String email) {
      return userRepository.findUserByEmail(email);
   }

   /**
    * loginUser will log in a user that exists in the database as long as their credentials (email, password) match, and generates a new JTW
    * key
    * @param loginRequest user credentials (email, password)
    * @return JWT key
    */
   public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
      try {
         // Authenticates the user by checking the email and password provided
         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
         // Sets the authenticated user in the SecurityContext
         SecurityContextHolder.getContext().setAuthentication(authentication);
         // Obtains the user's details after authentication
         myUserDetails = (MyUserDetails) authentication.getPrincipal();
         // Generate a JWT key for the authenticated user
         final String JWT = jwtUtils.generateJwtToken(myUserDetails);
         // Return the JWT key
         return ResponseEntity.ok(new LoginResponse(JWT));
      } catch (Exception e) {
         // Returns a 401 status code if the authentication fails
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Error : username or password is incorrect"));
      }
   }
}

