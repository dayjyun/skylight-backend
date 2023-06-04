package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Flight;
import com.skylight.models.Ticket;
import com.skylight.repositories.FlightRepository;
import com.skylight.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
   private FlightRepository flightRepository;
   private TicketRepository ticketRepository;

   @Autowired
   public void setFlightRepository(FlightRepository flightRepository) {
      this.flightRepository = flightRepository;
   }

   @Autowired
   public void setTicketRepository(TicketRepository ticketRepository) {
      this.ticketRepository = ticketRepository;
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

   /* !!!!!!!!!!!!!!!!!!!!!!!!!!!! */
   public Flight createFlight(Flight flight) {
      // check that the date does not conflict with other dates
      // check that the pilot is available at that time.
      // Factor in layover time (60 minutes)
      // add number of seats available
      // Adds (logged-in) user as a list of passengers
      // pilot is logged-in user
         // flight.setPilot(pilot)
      return flight;
   }

   /**
    * getFlightById returns a flight by its ID
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

   /**
    * deleteFlightById deletes flight by ID
    * A NotFoundException is thrown if the flight is not found with the provided ID
    * @param flightId is the flight ID to search by
    * @return the deleted flight data
    */
   public Optional<Flight> deleteFlightById(Long flightId) {
      // Create an optional of a flight
      Optional<Flight> flight = flightRepository.findFlightByIdAndPilotId(flightId, MyProfileService.getLoggedInUser().getId());
      // Check if the flight is present
      if(flight.isPresent()) {
         // Delete the flight
         flightRepository.deleteById(flightId);
         // Return the deleted flight
         return flight;
      }
      // Throw a NotFoundException if the flight is not found
      throw new NotFoundException("Flight " + flightId + " not found");
   }

   /**
    * getFlightTickets returns a list of tickets for a flight
    * A NotFoundException is thrown if the flight is not found with the provided ID
    * A NotFoundException is thrown if no tickets are found for the flight
    * @param flightId is the flight ID to search by
    * @return a list of tickets
    */
   public List<Ticket> getFlightTickets(Long flightId) {
      // Create an optional of a flight
      Optional<Flight> flight = flightRepository.findById(flightId);
      // Check if the flight is present
      if(flight.isPresent()) {
         //  Get the list of tickets for the flight
         List<Ticket> tickets = ticketRepository.findTicketByFlightId(flightId);
         // Check if the list of tickets is empty
         if(tickets.isEmpty()) {
            // Throw a NotFoundException if no tickets are found
            throw new NotFoundException("No tickets found");
         }
         // Return the list of tickets for the flight
         return tickets;
      }
      // Throw a NotFoundException if the flight is not found
      throw new NotFoundException("Flight " + flightId + " not found");
   }

   /**
    * createTicketForFlight creates a new ticket for an existing flight that belongs to the logged-in user
    * A NotFoundException is thrown if the flight is not found with the provided ID
    * @param flightId is the flight ID to search by
    * @return newly created ticket
    */
   public ResponseEntity<Ticket> createTicketForFlight(Long flightId) {
      // Create an optional of a flight
      Optional<Flight> flight = flightRepository.findFlightByIdAndPilotId(flightId, MyProfileService.getLoggedInUser().getId());
      // Check if the flight is present
      if (flight.isPresent()) {
         // Create a new ticket
         Ticket ticket = new Ticket();
         // Add flight to ticket
         ticket.setFlight(flight.get());
         // Add the ticket to the flight
         flight.get().getListOfTickets().add(ticket);
         // Save ticket to ticket repository
         ticketRepository.save(ticket);
         // Return the ticket with a 201 status code
         return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
      }
      // Throw a NotFoundException if the flight is not found
      throw new NotFoundException("Flight " + flightId + " not found");
   }
}
