package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Ticket;
import com.skylight.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
   private TicketRepository ticketRepository;

   @Autowired
   public void setTicketRepository(TicketRepository ticketRepository) {
      this.ticketRepository = ticketRepository;
   }

   // get ticket details
   public Optional<Ticket> getTicketById(Long ticketId) {
      return ticketRepository.findById(ticketId);
   }

   // delete ticket details
   public Optional<Ticket> deleteTicket(Long ticketId) {
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      if(ticket.isPresent()) {
         ticketRepository.deleteById(ticketId);
      }
      throw new NotFoundException("Ticket not found");
   }
}
