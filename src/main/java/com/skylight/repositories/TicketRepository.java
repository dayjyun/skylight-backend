package com.skylight.repositories;
import com.skylight.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

   List<Ticket> findTicketByFlightId(Long flightId);

}
