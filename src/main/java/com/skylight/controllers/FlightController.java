package com.skylight.controllers;

import com.skylight.models.Flight;
import com.skylight.models.Ticket;
import com.skylight.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

   @Autowired
   private FlightService flightService;

   /**
    * Functionality: Returns a list of all flights (Public)
    * Path: /api/flights
    * @return a list of flights
    */
   @GetMapping(path = "")
   public List<Flight> getAllFlights() {
      return flightService.getAllFlights();
   }

   /**
    * Functionality: Returns flight details (Public)
    * Path: /api/flights/{flightId}
    * @param flightId is the flight ID to search by
    * @return a Flight
    */
   @GetMapping(path = "/{flightId}")
   public Optional<Flight> getFlightById(@PathVariable Long flightId) {
      return flightService.getFlightById(flightId);
   }

   /**
    * Functionality: Admin deletes flight details (Private)
    * Path: /api/flights/{flightId}
    * @param flightId is the flight ID to search by
    * @return the deleted flight data
    */
   @DeleteMapping(path = "/{flightId}")
   public Optional<Flight> deleteFlightById(@PathVariable Long flightId) {
      return flightService.deleteFlightById(flightId);
   }

   /**
    * Functionality: Get all tickets available for flight (Public)
    * Path: /api/flights/{flightId}/tickets
    * @param flightId is the flight ID to search by
    * @return a list of tickets
    */
   @GetMapping(path = "/{flightId}/tickets")
   public List<Ticket> getFlightTickets(@PathVariable Long flightId) {
      return flightService.getFlightTickets(flightId);
   }

   /**
    * Functionality: Admin creates ticket for flight (Private)
    * Path: /api/flights/{flightId}/tickets
    * @param flightId is the flight ID to search by
    * @return newly created ticket
    */
   @PostMapping(path = "/{flightId}/tickets")
   public ResponseEntity<Ticket> createTicketForFlight(@PathVariable Long flightId) {
      return flightService.createTicketForFlight(flightId);
   }
}
