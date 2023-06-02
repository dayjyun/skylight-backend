package com.skylight.seed;

import com.skylight.models.Airport;
import com.skylight.models.Flight;
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
         // Admins/Pilots
         User aUser = new User(1L, "a", "a@email.com", "pw", true);
         User bUser = new User(2L, "b", "b@email.com", "pw", true);
         User cUser = new User(3L, "c", "c@email.com", "pw", true);

         // Passengers
         User dUser = new User(4L, "d", "d@email.com", "pw", false);
         User eUser = new User(5L, "e", "e@email.com", "pw", false);
         User fUser = new User(6L, "f", "f@email.com", "pw", false);
         User gUser = new User(6L, "g", "g@email.com", "pw", false);
         User hUser = new User(6L, "h", "h@email.com", "pw", false);

         userRepository.save(aUser);
         userRepository.save(bUser);
         userRepository.save(cUser);

         // Create Airports
         Airport midway =  new Airport(1L, "Midway", "MDW", "Chicago", "IL", "41.7865 N",  "87.6298 W");
         Airport ohare = new Airport(2L, "O'Hare", "OHX", "Chicago", "IL", "41.8781 N",  "87.6298 W");
         Airport phoenix = new Airport(3L, "Phoenix", "PHX", "Phoenix", "AZ", "33.4484 N",  "112.0740 W");
         Airport austin =  new Airport(4L, "Austin", "AUS", "Austin", "TX", "30.2672 N",  "97.7431 W");

         airportRepository.save(midway);
         airportRepository.save(ohare);
         airportRepository.save(phoenix);
         airportRepository.save(austin);

         Flight flightFromMDWtoAUS = new Flight(1L, "Boeing 737", "07-01-2023", "0900", 0,"1200",  1124);
         Flight flightFromPHXtoAUS = new Flight(2L, "Boeing 737", "07-01-2023", "0900", 0,"1200",  1124);
         Flight flightFromAUStoORD = new Flight(3L, "Boeing 737", "07-01-2023", "0900", 0,"1200",  1124);
         Flight flightFromAUStoPHX = new Flight(4L, "Boeing 737", "07-01-2023", "1300", 0,"1600",  1133);

         flightFromMDWtoAUS.setOriginAirport(midway);
         flightFromMDWtoAUS.setDestinationAirport(austin);
         flightFromMDWtoAUS.setPilot(aUser);

         flightFromPHXtoAUS.setOriginAirport(phoenix);
         flightFromPHXtoAUS.setDestinationAirport(austin);
         flightFromPHXtoAUS.setPilot(bUser);

         flightRepository.save(flightFromMDWtoAUS);
         flightRepository.save(flightFromPHXtoAUS);
         flightRepository.save(flightFromAUStoORD);
         flightRepository.save(flightFromAUStoPHX);
      }
   }
}
