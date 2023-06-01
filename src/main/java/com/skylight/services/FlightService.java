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

   public List<Flight> getAllFlights() {
      List<Flight> allFlights = flightRepository.findAll();
      if(allFlights.isEmpty()) {
         throw new NotFoundException("No flights found");
      }
      return allFlights;
   }

   /**
    * getFlightById returns a flight by its id
    * A NotFoundException is thrown if a flight is not found with the provided ID
    * @param flightId is the flight ID to search by
    * @return a Flight
    */
   public Optional<Flight> getFlightById(Long flightId) {
      Optional<Flight> flight = flightRepository.findById(flightId);
      if(flight.isPresent()) {
         return flight;
      }
      throw new NotFoundException("Flight not found");
   }
}
