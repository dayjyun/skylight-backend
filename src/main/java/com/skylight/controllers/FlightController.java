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

   @GetMapping(path = "")
   public List<Flight> getAllFlights() {
      return flightService.getAllFlights();
   }

   @GetMapping(path = "/{flightId}")
   public Optional<Flight> getFlightById(@PathVariable Long flightId) {
      return flightService.getFlightById(flightId);
   }
}
