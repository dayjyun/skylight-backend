package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Airport;
import com.skylight.models.Flight;
import com.skylight.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private AirportRepository airportRepository;

    @Autowired
   public void setAirportRepository(AirportRepository airportRepository) {
       this.airportRepository = airportRepository;
    }

   /**
    * allAirports returns a list of all airports in the database
    * A NotFoundException is thrown if there are no airports in the database
    * @return a list of all airports
    */
   public List<Airport> getAllAirports() {
      // Create a list of airports
       List<Airport> allAirports = airportRepository.findAll();
       // Check if the list of airports is empty
       if(allAirports.isEmpty()) {
          // Throw an error if no airports are found
          throw new NotFoundException("No airports found");
       }
       // Return the list of all airports in the database
       return allAirports;
    }

   /**
    * getAirportById returns an airport by its id
    * A NotFoundException is thrown if an airport is not found with the provided ID
    * @param airportId is the airport ID to search by
    * @return Airport
    */
   public Optional<Airport> getAirportById(Long airportId) {
      // Create an optional of an airport
      Optional<Airport> airport = airportRepository.findById(airportId);
      // Check if the airport is present
      if(airport.isPresent()) {
         // Return the airport data
         return airport;
      }
      // Throw an error if the airport is found
      throw new NotFoundException("No airport found");
   }

   /**
    * getArrivals returns a list of all arriving flights for an airport
    * A NotFoundException is thrown if an airport is not found with the provided ID
    * @param airportId is the airport ID to search by
    * @return list of flights
    */
   public List<Flight> getDepartures(Long airportId) {
      // Create an optional of an airport
      Optional<Airport> airport = airportRepository.findById(airportId);
      // Check if the airport is present
      if(airport.isPresent()) {
         // Return the airport data
         return airport.get().getDepartingFlightsList();
      }
      // Throw an error if the airport is found
      throw new NotFoundException("No airport found");
   }
   // Return list of arriving Flights
}
