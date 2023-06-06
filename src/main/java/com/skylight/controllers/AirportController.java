package com.skylight.controllers;

import com.skylight.models.Airport;
import com.skylight.models.Flight;
import com.skylight.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

   @Autowired
   private AirportService airportService;

   /**
    * Functionality: Returns a list of airports	(Public)
    * Path: /api/airports
    * @return list of airports
    */
   @GetMapping(path = "")
   public List<Airport> getAllAirports() {
      return airportService.getAllAirports();
   }

   /**
    * Functionality: Returns details of an airport (Public)
    * Path: /api/airports/{airportId}
    * @param airportId is the airport ID to search by
    * @return airport
    */
   @GetMapping(path = "/{airportId}")
   public Optional<Airport> getAirportById(@PathVariable Long airportId) {
      return airportService.getAirportById(airportId);
   }

   /**
    * Functionality: Returns details of an airport by code (Public)
    * Path: /api/airports/code/{airportCode}
    * @param airportCode is the airport code to search by
    * @return airport
    */
   @GetMapping(path = "/code/{airportCode}")
   public Optional<Airport> getAirportByCode(@PathVariable String airportCode) {
      return airportService.getAirportByCode(airportCode);
   }

   /**
    * Functionality: Admin creates a new flight origin (ID/Code) (Private)
    * Path: /api/airports/{airportId}/arrivals
    * @param airportCode is the origin airport details
    * @param departingFlight is the origin flight data
    * @return flight departure details
    */
   @PostMapping(path = "/code/{airportCode}/origin")
   public Flight createFlightOrigin(@PathVariable String airportCode, @RequestBody Flight departingFlight) {
      return airportService.createFlightOrigin(airportCode, departingFlight);
   }

   /**
    * Functionality: Admin creates a new flight destination (Private)
    * Path: /api/airports/code/{airportCode}/destination
    * @param airportCode is the destination airport details
    * @param arrivingFlight is the destination flight data
    * @return flight departure details
    */
   @PostMapping(path = "/code/{airportCode}/destination")
   public Flight createFlightDestination(@PathVariable String airportCode, @RequestBody Flight arrivingFlight) {
      return airportService.createFlightDestination(airportCode, arrivingFlight);
   }

   /**
    * Functionality: Returns list of arrivals for an airport (Public)
    * Path: /api/airports/{airportId}/arrivals
    * @param airportId is the airport ID to search by
    * @return list of flights
    */
   @GetMapping(path = "/{airportId}/arrivals")
   public List<Flight> getArrivals(@PathVariable Long airportId) {
      return airportService.getArrivals(airportId);
   }

   /**
    * Functionality: Returns list of departures for an airport (Public)
    * Path: /api/airports/{airportId}/departures
    * @param airportId is the airport ID to search by
    * @return list of flights
    */
   @GetMapping(path = "/{airportId}/departures")
   public List<Flight> getDepartures(@PathVariable Long airportId) {
      return airportService.getDepartures(airportId);
   }

}
