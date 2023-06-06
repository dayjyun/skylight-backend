package com.skylight.services;

import com.skylight.exceptions.NotFoundException;
import com.skylight.models.Ticket;
import com.skylight.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    * getTicketById returns a ticket by its ID only if you're the ticket holder, you're the pilot for the given flight, or if the ticket
    * is still available
    * A NotFoundException is thrown if the ticket is not found with the provided ID
    * @param ticketId is the ticket ID to search by
    * @return a ticket
    */
   public Optional<Ticket> getTicketById(Long ticketId) {
      // Create an optional of a ticket
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      // Check if the ticket is present
      if (ticket.isPresent()) {
         //  Check if the ticket has a passenger
         if (ticket.get().getPassenger() == null ||
                 // Check if the ticket belongs to the logged-in user
                 Objects.equals(ticket.get().getPassenger().getId(), MyProfileService.getLoggedInUser().getId()) ||
                 // Check if the ticket belongs to a flight created by the logged-in user
                 Objects.equals(ticket.get().getFlight().getPilot().getId(), MyProfileService.getLoggedInUser().getId())
         ) {
            // Return the ticket data
            return ticket;
         }
      }
      // Throw a NotFoundException if the ticket is not found
      throw new NotFoundException("Ticket " + ticketId + " not found");
   }

   /**
    * deleteTicket deletes a ticket by its ID A NotFoundException is thrown if the ticket is not found with the provided ID
    * A NotFoundException is thrown if the ticket is not found with the provided ID
    * @param ticketId is the ticket ID to search by
    * @return the deleted ticket data
    */
   public Optional<Ticket> deleteTicketAdmin(Long ticketId) {
      // Create an optional for a ticket
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      // Check if the ticket is present and check ticket belongs to the flight made by the logged-in user or the ticket owner
      if (ticket.isPresent() && Objects.equals(ticket.get().getFlight().getPilot().getId(), MyProfileService.getLoggedInUser().getId()) || Objects.equals(ticket.get().getPassenger().getId(), MyProfileService.getLoggedInUser().getId()) ) {
         // Delete the ticket data
         ticketRepository.deleteById(ticketId);
         // Return the deleted ticket data
         return ticket;
      }
      // Throw a NotFoundException if the ticket is not found
      throw new NotFoundException("Ticket " + ticketId + " not found");
   }

   /**
    * bookFlight allows the logged-in user to book a flight by searching the ticket number An AlreadyExistsException is thrown if the user
    * is already booked on the flight
    * A NotFoundException is thrown if the ticket is not found with the provided ID
    * @param ticketId is the ticket ID to search by
    * @return the booked ticket data
    */
   public Optional<Ticket> bookFlight(Long ticketId) {
      // Create an optional for a ticket
      Optional<Ticket> ticket = ticketRepository.findById(ticketId);
      // Check if the ticket is present and is available
      if (ticket.isPresent() && ticket.get().getPassenger() == null) {
         // Obtain details of the logged-in user. Only logged-in users can book a flight
         List<Ticket> myTicketsList = MyProfileService.getLoggedInUser().getMyTicketsList();
         // Add ticket to user's tickets list
         myTicketsList.add(ticket.get());
         // Add logged-in user as the owner of the ticket
         ticket.get().setPassenger(MyProfileService.getLoggedInUser());
         // Return the ticket data
         return ticket;
      }
      // Throw a NotFoundException if the ticket is not found
      throw new NotFoundException("Ticket " + ticketId + " not found");
   }
}
