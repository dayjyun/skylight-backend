package com.skylight.seed;

import com.skylight.models.Airport;
import com.skylight.models.User;
import com.skylight.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Data implements CommandLineRunner {
   @Autowired
   AirportRepository airportRepository;

   @Autowired
   FlightRepository flightRepository;

   @Autowired
   TicketRepository ticketRepository;

   @Autowired
   UserRepository userRepository;

   @Override
   public void run(String... args) throws Exception {
      loadSeedData();
   }

   private void loadSeedData() {
      if (airportRepository.count() == 0 && flightRepository.count() == 0 && ticketRepository.count() == 0 && userRepository.count() == 0) {

         // Create Users
         User a = new User(1L, "a", "a@email.com", "pw", true);
         User b = new User(2L, "b", "b@email.com", "pw", false);
         User c = new User(2L, "c", "c@email.com", "pw", false);

         userRepository.save(a);
         userRepository.save(b);
         userRepository.save(c);

         // Create Airports
         Airport midway =  new Airport(1L, "Midway", "MDW", "Chicago", "IL", "41.7865 N",  "87.6298 W");
         Airport phoenix = new Airport(2L, "Phoenix", "PHX", "Phoenix", "AZ", "33.4484 N",  "112.0740 W");
         Airport austin =  new Airport(3L, "Austin", "AUS", "Austin", "TX", "30.2672 N",  "97.7431 W");

         airportRepository.save(midway);
         airportRepository.save(phoenix);
         airportRepository.save(austin);
      }
   }
}
