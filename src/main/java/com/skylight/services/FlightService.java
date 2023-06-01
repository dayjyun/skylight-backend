package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Flight;
import com.skylight.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
   private FlightRepository flightRepository;

   @Autowired
   public void setFlightRepository(FlightRepository flightRepository) {
      this.flightRepository = flightRepository;
   }

   /**
    * getAllFlights returns all flights in the database
    * A NotFoundException is thrown if there are no flights in the database
    * @return a list of flights
    */

   public List<Flight> getAllFlights() {
      // Create a list of flights
      List<Flight> allFlights = flightRepository.findAll();
      // Check if the list of flights is empty
      if(allFlights.isEmpty()) {
         // Throw a NotFoundException if no flights are found
         throw new NotFoundException("No flights found");
      }
      // Return the list of all flights in the database
      return allFlights;
   }

   public Flight createFlight(Flight flight) {
      // check that the date does not conflict with other dates
      // check that the pilot is available at that time.
   }

   /**
    * getFlightById returns a flight by its id
    * A NotFoundException is thrown if a flight is not found with the provided ID
    * @param flightId is the flight ID to search by
    * @return a Flight
    */
   public Optional<Flight> getFlightById(Long flightId) {
      // Create an optional of a flight
      Optional<Flight> flight = flightRepository.findById(flightId);
      // Check if the flight is present
      if(flight.isPresent()) {
         // Return the flight data
         return flight;
      }
      // Throw a NotFoundException if the flight is not found
      throw new NotFoundException("Flight not found");
   }
}
