package com.skylight.repositories;


import com.skylight.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
   List<Flight> findFlightByOriginAirportId(Long airportId);
   List<Flight> findFlightByDestinationAirportId(Long airportId);

   Optional<Flight> findFlightByIdAndPilotId(Long flightId, Long pilotId);

   List<Flight> findAllByPilotId(Long pilotId);
}
