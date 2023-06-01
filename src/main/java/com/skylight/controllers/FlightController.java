package com.skylight.controllers;

import com.skylight.models.Flight;
import com.skylight.models.User;
import com.skylight.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

   @Autowired
   private FlightService flightService;

   // Functionality: Returns a list of all flights (Public)
   // Path: /api/flights
   @GetMapping(path = "")
   public List<Flight> getAllFlights() {
      return flightService.getAllFlights();
   }

   // Functionality: Users create a new flight (Public | Private)
   // Path: /api/flights
   @PostMapping(path = "")
   public Flight createFlight(@RequestBody Flight flight) {
      return flightService.createFlight(flight);
   }

   // Functionality: Returns flight details (Public)
   // Path: /api/flights/{flightId}
   @GetMapping(path = "/{flightId}")
   public Optional<Flight> getFlightById(@PathVariable Long flightId) {
      return flightService.getFlightById(flightId);
   }

   // Functionality: Delete flight (Public | Private)
   // Path: /api/flights/{flightId}
   @DeleteMapping(path = "/{flightId})")
   public Optional<Flight> deleteFlightById(@PathVariable Long flightId) {
      return flightService.deleteFlightById(flightId);
   }

   // Functionality: Purchase ticket for flight (Public | Public)
   // Path: /api/flights/{flightId}/tickets
   @PostMapping(path = "/{flightId}/tickets")
   public Flight purchaseTicket(@PathVariable Long flightId, @RequestBody User user) {
      return flightService.purchaseTicket(flightId, user);
   }

   // Functionality: Get all tickets for flight (Public | Private)
   // Path: /api/flights/{flightId}/tickets

   // Functionality: Delete ticket for flight (Public | Private)
   // Path: /api/flights/{flightId}/tickets
}
