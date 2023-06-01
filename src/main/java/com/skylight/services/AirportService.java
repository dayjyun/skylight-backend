package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Airport;
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
    * A NotFoundException is thrown if no airports are found
    * @return List of all airports
    */
   public List<Airport> getAllAirports() {
      // Create a list of airports
       List<Airport> allAirports = airportRepository.findAll();
       // Check if the list is empty
       if(allAirports.isEmpty()) {
          // Throw an error if not airports are found
          throw new NotFoundException("No airports found");
       }
       // Return the list of all airports if not empty
       return allAirports;
    }

   /**
    * getAirportById returns an airport by its id
    * A NotFoundException is thrown if no airport is found
    * @param airportId is the airport to search
    * @return Airport
    */
   public Optional<Airport> getAirportById(Long airportId) {
      // Create an optional of an airport
      Optional<Airport> airport = airportRepository.findById(airportId);
      // Check if the optional is present
      if(airport.isPresent()) {
         // Return the airport data
         return airport;
      }
      // Throw an error if no airport is found
      throw new NotFoundException("No airport found");
   }
}
