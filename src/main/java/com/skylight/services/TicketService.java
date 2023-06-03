package com.skylight.services;

import com.skylight.exceptions.AlreadyExistsException;
import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Ticket;
import com.skylight.models.User;
import com.skylight.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {
   private TicketRepository ticketRepository;

   @Autowired
   public void setTicketRepository(TicketRepository ticketRepository) {
      this.ticketRepository = ticketRepository;
   }

   /**
    * getTicketById returns a ticket by its ID
    * A NotFoundException is thrown if the ticket is not found with the provided ID
    * @param ticketId is the ticket ID to search by
    * @return a ticket
    */
   public Optional<Ticket> getTicketById(Long ticketId) {
      // Create an optional of a ticket
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      // Check if the ticket is present
      if(ticket.isPresent()) {
         // Return the ticket data
         return ticket;
      }
      // Throw a NotFoundException if the ticket is not found
      throw new NotFoundException("Ticket not found");
   }

   /**
    * deleteTicket deletes a ticket by its ID
    * A NotFoundException is thrown if the ticket is not found with the provided ID
    * @param ticketId is the ticket ID to search by
    * @return the deleted ticket data
    */
   public Optional<Ticket> deleteTicket(Long ticketId) {
      // Create an optional for a ticket
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      // Check if the ticket is present
      if(ticket.isPresent()) {
         // Delete the ticket data
         ticketRepository.deleteById(ticketId);
         // Return the deleted ticket data
         return ticket;
      }
      // Throw a NotFoundException if the ticket is not found
      throw new NotFoundException("Ticket " + ticketId + " not found");
   }

   /**
    * bookFlight allows the user to book a flight by searching the ticket number
    * An AlreadyExistsException is thrown if the user is already booked on the flight
    * A NotFoundException is thrown if the ticket is not found with the provided ID
    * @param ticketId is the ticket ID to search by
    * @param user is the user to book the flight for
    * @return the booked ticket data
    */
   public Ticket bookFlight(Long ticketId, User user) {
      // Create an optional for a ticket
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      //Check the flight information, make sure the user is not already booked on the flight
      if(ticket.isPresent() && Objects.equals(ticket.get().getPassenger().getId(), user.getId())) {
         // Throw an AlreadyExistsException if the user is already booked on the flight
         throw new AlreadyExistsException("User " + user.getId() + " is already booked on flight " + ticketId);
      }

      // Check passenger is null.
      // If passenger is null || empty, users can view ticket
      // else unauthorized

      // Check if the ticket is present
      if(ticket.isPresent()) {
         // Add user as the owner of the ticket
         ticket.get().setPassenger(user);
         // Add ticket to user's tickets list
         user.getMyTicketsList().add(ticket.get());
         // Save the ticket
         ticketRepository.save(ticket.get());
         // Return the ticket data
         return ticket.get();
      }
      // Throw a NotFoundException if the ticket is not found
      throw new NotFoundException("Ticket " + ticketId + " not found");
   }
}
