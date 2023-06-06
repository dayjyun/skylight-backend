package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Airport;
import com.skylight.models.Flight;
import com.skylight.repositories.AirportRepository;
import com.skylight.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AirportService {

    private AirportRepository airportRepository;
    private FlightRepository flightRepository;

    @Autowired
    public void setAirportRepository(AirportRepository airportRepository) {
       this.airportRepository = airportRepository;
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
       this.flightRepository = flightRepository;
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
    * getAirportByCode returns an airport by its code
    * A NotFoundException is thrown if an airport is not found with the provided ID
    * @param airportCode is the airport code to search by
    * @return Airport
    */
   public Optional<Airport> getAirportByCode(String airportCode) {
      // Create an optional of an airport
      Optional<Airport> airport = airportRepository.findAirportByAirportCodeIgnoreCase(airportCode);
      // Check if the airport is present
      if(airport.isPresent()) {
         // Return the airport data
         return airport;
      }
      // Throw an error if the airport is found
      throw new NotFoundException("No airport found");
   }

   /**
    * createFlightOrigin creates a new flight destination
    * A NotFoundException is thrown if there are no airports found with the provided code
    * @param airportCode is the origin airport details
    * @param departingFlight is the origin flight data
    * @return flight departure details
    */
   public Flight createFlightOrigin(String airportCode, Flight departingFlight) {
      // Create an optional of the target airport
      Optional<Airport> airport = airportRepository.findAirportByAirportCodeIgnoreCase(airportCode);
      // Check if the airport is present
      if(airport.isPresent()) {
         // Set origin airport to the flight
         departingFlight.setOriginAirport(airport.get());
         // Set the pilot to be the logged-in user
         departingFlight.setPilot(MyProfileService.getLoggedInUser());
         // Save the new flight
         flightRepository.save(departingFlight);
         // Add flight to list of departures for the airport
         airport.get().getDepartingFlightsList().add(departingFlight);
         // Save the updated airport data
         airportRepository.save(airport.get());
         // Return the departing flight details
         return departingFlight;
      }
      // Throw an error if the airport is found
      throw new NotFoundException("No airport found");
   }

   /**
    * createFlightDestination creates a new flight destination
    * A NotFoundException is thrown if there are no airports found with the provided code
    * @param airportCode is the destination airport details
    * @param arrivingFlight is the destination flight data
    * @return flight departure and destination details
    */
   public Flight createFlightDestination(String airportCode, Flight arrivingFlight) {
      // Create an optional of the target airport
      Optional<Airport> airport = airportRepository.findAirportByAirportCodeIgnoreCase(airportCode);
      // Check if the airport exists
      if (airport.isPresent()) {
         // Return existing flight details that belong to the logged-in user
         Optional<Flight> existingFlight = flightRepository.findFlightByIdAndPilotId(arrivingFlight.getId(),
                 MyProfileService.getLoggedInUser().getId());
         // Update the existing flight details with the arrival details
         // Arrival date
         existingFlight.get().setArrivalDate(arrivingFlight.getArrivalDate());
         // Arrival time
         existingFlight.get().setArrivalTime(arrivingFlight.getArrivalTime());
         // Layover time
         if(arrivingFlight.getLayoverTime() == null) {
            existingFlight.get().setLayoverTime(0);
         } else {
            existingFlight.get().setLayoverTime(arrivingFlight.getLayoverTime());
         }
         // Set destination airport to the flight
         existingFlight.get().setDestinationAirport(airport.get());
         // Save the new flight
         flightRepository.save(existingFlight.get());
         // Add flight to list of arrivals for the airport
         airport.get().getArrivingFlightsList().add(existingFlight.get());
         // Save the updated airport data
         airportRepository.save(airport.get());
         // Return the arriving flight details
         return existingFlight.get();
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
   public List<Flight> getArrivals(Long airportId) {
      // Create an optional of an airport
      Optional<Airport> airport = airportRepository.findById(airportId);
      // Check if the airport is present
      if(airport.isPresent()) {
         // Create a list of arrival flights
         List<Flight> arrivalsList = flightRepository.findFlightByDestinationAirportId(airportId);
         // Check if the list of arrival flights is empty
         if(arrivalsList.isEmpty()) {
            // Throw an error if no flights are found
            throw new NotFoundException("No arrival flights found");
         }
         // Return the airport data
         return arrivalsList;
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
         // Create a list of departure flights
         List<Flight> departuresList = flightRepository.findFlightByOriginAirportId(airportId);
         // Check if the list of departure flights is empty
         if(departuresList.isEmpty()) {
            // Throw an error if no flights are found
            throw new NotFoundException("No departure flights found");
         }
         // Return the airport data
         return departuresList;
      }
      // Throw an error if the airport is found
      throw new NotFoundException("No airport found");
   }
}
