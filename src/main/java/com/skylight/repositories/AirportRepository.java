package com.skylight.repositories;

import com.skylight.models.Airport;
import com.skylight.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

   Optional<Airport> findAirportByAirportCodeIgnoreCase(String airportCode);

}
