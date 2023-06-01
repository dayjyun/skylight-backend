package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Ticket;
import com.skylight.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

   // delete ticket details
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
      throw new NotFoundException("Ticket not found");
   }
}
