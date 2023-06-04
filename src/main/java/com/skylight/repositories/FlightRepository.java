package com.skylight.repositories;


import com.skylight.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
   List<Flight> findFlightByOriginAirportId(Long airportId);
   List<Flight> findFlightByDestinationAirportId(Long airportId);
}
