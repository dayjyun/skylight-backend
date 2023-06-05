package com.skylight.controllers;

import com.skylight.models.Airport;
import com.skylight.models.Flight;
import com.skylight.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
   public List<Airport> getAllAirports() {
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



   /* !!!!!!!!!!!!!!!!!!!!!!!!!!!! */
//    Functionality: Admin creates a new flight origin (ID/Code) (Private)
//    Path: /api/airports/{airportId}/arrivals
   @PostMapping(path = "/code/{airportCode}/origin")
   public Flight createFlightOrigin(@PathVariable String airportCode, @RequestBody Flight departingFlight) {
      return airportService.createFlightOrigin(airportCode, departingFlight);
   }

   // Functionality: Admin creates a new flight destination (ID/Code) (Private)
   // Path: /api/airports/code/{airportCode}/destination
   @PostMapping(path = "/code/{airportCode}/destination")
   public Flight createFlightDestination(@PathVariable String airportCode, @RequestBody Flight arrivingFlight) {
      return airportService.createFlightDestination(airportCode, arrivingFlight);
   }

   @PostMapping(path = "/code/{originAirportCode}/{destinationAirportCode}")
   public Flight createFlight(@PathVariable String originAirportCode, @PathVariable String destinationAirportCode,
                                    @RequestBody Flight flightDetails) {
      return airportService.createFlight(originAirportCode, destinationAirportCode, flightDetails);
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
