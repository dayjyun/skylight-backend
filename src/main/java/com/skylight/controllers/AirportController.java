package com.skylight.controllers;

import com.skylight.models.Airport;
import com.skylight.models.Flight;
import com.skylight.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

   @Autowired
   private AirportService airportService;

   // Functionality: Returns a list of airports	(Public)
   // Path: /api/airports
   @GetMapping(path = "")
   public List<Airport> getAirports() {
      return airportService.getAllAirports();
   }

   // Functionality: Returns details of an airport (Public)
   // Path: /api/airports
   @GetMapping(path = "/{airportId}")
   public Optional<Airport> getAirportById(@PathVariable Long airportId) {
      return airportService.getAirportById(airportId);
   }

   // Functionality: Returns details of an airport by code (Public)
   // Path: /api/airports/code/{airportCode}
   @GetMapping(path = "/code/{airportCode}")
   public Optional<Airport> getAirportByCode(@PathVariable String airportCode) {
      return airportService.getAirportByCode(airportCode);
   }

   // Functionality: Returns list of arrivals for an airport (Public)
   // Path: /api/airports/{airportId}/arrivals
   @GetMapping(path = "/{airportId}/arrivals")
   public List<Flight> getArrivals(@PathVariable Long airportId) {
      return airportService.getArrivals(airportId);
   }

   // Functionality: Returns list of departures for an airport (Public)
   // Path: /api/airports/{airportId}/departures
   @GetMapping(path = "/{airportId}/departures")
   public List<Flight> getDepartures(@PathVariable Long airportId) {
      return airportService.getDepartures(airportId);
   }

}
