package com.skylight.services;

import com.skylight.models.Airport;
import com.skylight.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private AirportRepository airportRepository;

    @Autowired
   public void setAirportRepository(AirportRepository airportRepository) {
       this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
       return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Long airportId) {
       return airportRepository.findById(airportId);
    }
}
