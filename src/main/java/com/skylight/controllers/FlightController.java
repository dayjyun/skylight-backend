package com.skylight.controllers;

import com.skylight.models.Flight;
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
}
