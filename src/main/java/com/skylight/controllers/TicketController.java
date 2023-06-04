package com.skylight.controllers;

import com.skylight.models.Ticket;
import com.skylight.models.User;
import com.skylight.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

   @Autowired
   private TicketService ticketService;

   // Functionality: Returns ticket details (Public | Private)
   // Path: /api/tickets/{ticketId}
   @GetMapping(path = "/{ticketId}")
   public Optional<Ticket> getTicketById(@PathVariable Long ticketId) {
      return ticketService.getTicketById(ticketId);
   }

   // Functionality: Admin deletes ticket (Public/Private)
   // Path: /api/tickets/{ticketId}
   @DeleteMapping(path = "/{ticketId}")
   public Optional<Ticket> deleteTicketAdmin(@PathVariable Long ticketId) {
      return ticketService.deleteTicketAdmin(ticketId);
   }

   // Functionality: User books ticket (Public | Private)
   // Path: /api/tickets/{ticketId}/bookFlight
   public Ticket bookFlight(@PathVariable Long ticketId, @RequestBody User user) {
      return ticketService.bookFlight(ticketId, user);
   }
}
