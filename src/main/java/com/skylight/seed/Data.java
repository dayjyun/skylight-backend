package com.skylight.seed;

import com.skylight.models.Airport;
import com.skylight.models.Flight;
import com.skylight.models.Ticket;
import com.skylight.models.User;
import com.skylight.repositories.*;
import com.skylight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Data implements CommandLineRunner {
   @Autowired
   AirportRepository airportRepository;

   @Autowired
   FlightRepository flightRepository;

   @Autowired
   TicketRepository ticketRepository;

   @Autowired
   UserRepository userRepository;

   @Autowired
   UserService userService;

   @Override
   public void run(String... args) throws Exception {
      loadSeedData();
   }

   private void loadSeedData() {
      if (airportRepository.count() == 0 && flightRepository.count() == 0 && ticketRepository.count() == 0 && userRepository.count() == 0) {

         // Create Users
         // Admins/Pilots
         User kc = new User(1L, "KC", "k@email.com", "pw", true);
         User maksym = new User(2L, "Maksym", "maksym@email.com", "pw", true);
         User anakin = new User(3L, "Anakin Skywalker", "as@email.com", "pw", true);

         // Passengers
         User dominique = new User(4L, "Dominique", "dominique@email.com", "pw", false);
         User kim = new User(5L, "Kim", "kim@email.com", "pw", false);
         User jay = new User(6L, "Jay", "jay@email.com", "pw", false);
         User deShe = new User(7L, "DeShe", "deshe@email.com", "pw", false);
         User jeff = new User(8L, "Jeff", "jeff@email.com", "pw", false);

         // Save users

         List<User> usersList = new ArrayList<>(Arrays.asList(kc, maksym, anakin, dominique, kim, jay, deShe, jeff));
         for(User user : usersList) {
            userService.createUser(user);
         }

         // Create Airports
         Airport midwayAirport =  new Airport(1L, "Midway", "MDW", "Chicago", "IL", "41.7865 N",  "87.6298 W");
         Airport ohareAirport = new Airport(2L, "O'Hare", "ORD", "Chicago", "IL", "41.8781 N",  "87.6298 W");
         Airport austinAirport =  new Airport(3L, "Austin", "AUS", "Austin", "TX", "30.2672 N",  "97.7431 W");
         Airport phoenixAirport = new Airport(4L, "Phoenix", "PHX", "Phoenix", "AZ", "33.4484 N",  "112.0740 W");
         Airport dallasFortWorthAirport = new Airport(5L, "Dallas Fort Worth", "DFW", "Dallas", "TX", "32.8998 N",  "97.0403 W");
         Airport johnFKennedyAirport = new Airport(6L, "John F Kennedy", "JFK", "New York", "NY", "40.6446 N",  "73.7858 W");

         // Create flights
         Flight flightFromMDWtoAUS = new Flight(1L, "Boeing 737", "07-01-2023", "07-01-2023", "0900", 0,"1200",  1124);
         Flight flightFromPHXtoAUS = new Flight(2L, "Boeing 787", "07-01-2023", "07-01-2023", "0900", 0,"1100",  885);
         Flight flightFromAUStoORD = new Flight(3L, "Jedi Starfighter", "07-01-2023", "07-01-2023", "0900", 0,"1200",  978);
         Flight flightFromAUStoPHX = new Flight(4L, "Airbus A320", "07-01-2023", "07-01-2023", "1300", 0,"1530",  1133);
         Flight flightFromJFKtoDFW = new Flight(5L, "Boeing 747", "07-02-2023", "07-02-2023", "0800", 0, "1100", 1370);
         Flight flightFromORDtoJFK = new Flight(6L, "Airbus A380", "07-02-2023", "07-02-2023", "0900", 0, "1130", 740);

         // Save airports
         airportRepository.save(midwayAirport);
         airportRepository.save(ohareAirport);
         airportRepository.save(austinAirport);
         airportRepository.save(phoenixAirport);
         airportRepository.save(dallasFortWorthAirport);
         airportRepository.save(johnFKennedyAirport);


         // Set origin flights
         // Flight Midway to Austin
         ArrayList<Flight> originMDWAirport = new ArrayList<>();
         originMDWAirport.add(flightFromMDWtoAUS); // Flight 1
         midwayAirport.setDepartingFlightsList(originMDWAirport);
         // Flight Phoenix to Austin
         ArrayList<Flight> originPHXAirport = new ArrayList<>();
         originPHXAirport.add(flightFromPHXtoAUS); // Flight 2
         phoenixAirport.setDepartingFlightsList(originPHXAirport);
         // Flight Austin to Ohare
         // Flight Austin to Phoenix
         ArrayList<Flight> originAUSAirport = new ArrayList<>();
         originAUSAirport.add(flightFromAUStoORD); // Flight 3
         originAUSAirport.add(flightFromAUStoPHX); // Flight 4
         austinAirport.setDepartingFlightsList(originAUSAirport);


         // Set destination flights
         // Flight Midway to Austin
         // Flight Phoenix to Austin
         ArrayList<Flight> destinationAUSAirport = new ArrayList<>();
         destinationAUSAirport.add(flightFromMDWtoAUS); // Flight 1
         destinationAUSAirport.add(flightFromPHXtoAUS); // Flight 2
         austinAirport.setArrivingFlightsList(destinationAUSAirport);

         // Flight Austin to Ohare
         ArrayList<Flight> destinationORDAirport = new ArrayList<>();
         destinationORDAirport.add(flightFromAUStoORD); // Flight 3
         ohareAirport.setArrivingFlightsList(destinationORDAirport);

         // Flight Austin to Phoenix
         ArrayList<Flight> destinationPHXAirport = new ArrayList<>();
         destinationPHXAirport.add(flightFromAUStoPHX); // Flight 4
         austinAirport.setArrivingFlightsList(destinationPHXAirport);


         // Assign origin and destination airports to flights
         // Assign pilot to flights

         // Flight Midway to Austin
         // Pilot kc
         flightFromMDWtoAUS.setOriginAirport(midwayAirport);
         flightFromMDWtoAUS.setDestinationAirport(austinAirport);
         // Add flight to flight list for kc
         flightFromMDWtoAUS.setPilot(kc); // 1
         ArrayList<Flight> originMDWtoAUS = new ArrayList<>();
         originMDWtoAUS.add(flightFromMDWtoAUS);
         kc.setMyFlightsList(originMDWtoAUS);

         // Flight Phoenix to Austin
         // Pilot maksym
         flightFromPHXtoAUS.setOriginAirport(phoenixAirport);
         flightFromPHXtoAUS.setDestinationAirport(austinAirport);
         // Add flight to flight list for maksym
         flightFromPHXtoAUS.setPilot(maksym); // 2
         ArrayList<Flight> originPHXtoAUS = new ArrayList<>();
         originPHXtoAUS.add(flightFromPHXtoAUS);
         maksym.setMyFlightsList(originPHXtoAUS);

         // Flight Austin to Ohare
         // Pilot  anakin
         flightFromAUStoORD.setOriginAirport(austinAirport);
         flightFromAUStoORD.setDestinationAirport(ohareAirport);
         // Add flight to flight list for anakin
         flightFromAUStoORD.setPilot(anakin); // 3
         ArrayList<Flight> originAUStoORD = new ArrayList<>();
         originAUStoORD.add(flightFromAUStoORD);
         anakin.setMyFlightsList(originAUStoORD);

         // Flight Austin to Phoenix
         // Pilot kc
         flightFromAUStoPHX.setOriginAirport(austinAirport);
         flightFromAUStoPHX.setDestinationAirport(phoenixAirport);
         // Add flight to flight list for pilot 1
         flightFromAUStoPHX.setPilot(kc); // 1
         ArrayList<Flight> originAUStoPHX = new ArrayList<>();
         originAUStoPHX.add(flightFromAUStoORD);
         kc.setMyFlightsList(originAUStoPHX);

         // Flight JFK to DFW
         // Pilot maksym
         flightFromJFKtoDFW.setOriginAirport(johnFKennedyAirport);
         flightFromJFKtoDFW.setDestinationAirport(dallasFortWorthAirport);
         // Add flight to flight list for maksym
         flightFromJFKtoDFW.setPilot(maksym); // 2
         ArrayList<Flight> originJFKtoDFW = new ArrayList<>();
         originJFKtoDFW.add(flightFromJFKtoDFW);
         maksym.setMyFlightsList(originJFKtoDFW);

         // Flight ORD to JFK
         // Pilot anakin
         flightFromORDtoJFK.setOriginAirport(ohareAirport);
         flightFromORDtoJFK.setDestinationAirport(johnFKennedyAirport);
         // Add flight to flight list for anakin
         flightFromORDtoJFK.setPilot(anakin); // 3
         ArrayList<Flight> originORDtoJFK = new ArrayList<>();
         originORDtoJFK.add(flightFromORDtoJFK);
         anakin.setMyFlightsList(originORDtoJFK);

         // Save flight details
//         flightRepository.save(flightFromJFKtoDFW);
//         flightRepository.save(flightFromORDtoJFK);


         // Save flight details
         flightRepository.save(flightFromMDWtoAUS);
         flightRepository.save(flightFromPHXtoAUS);
         flightRepository.save(flightFromAUStoORD);
         flightRepository.save(flightFromAUStoPHX);
         flightRepository.save(flightFromJFKtoDFW);
         flightRepository.save(flightFromORDtoJFK);

         // Create tickets
         Ticket ticketFromMDWtoAUS = new Ticket(1L,  flightFromMDWtoAUS, dominique);
         Ticket ticketFromPHXtoAUS = new Ticket(2L, flightFromPHXtoAUS, kim);
         Ticket ticketFromAUStoORD = new Ticket(3L, flightFromAUStoORD, jay);
         Ticket ticketFromAUStoPHX = new Ticket(4L, flightFromAUStoPHX, deShe);
         Ticket ticketFromMDWtoAUS2 = new Ticket(5L, flightFromAUStoPHX, null);
         Ticket ticketFromJFKtoDFW = new Ticket(6L, flightFromJFKtoDFW, null);
         Ticket ticketFromORDtoJFK = new Ticket(7L, flightFromORDtoJFK, null);

         // Save ticket details
         ticketRepository.save(ticketFromMDWtoAUS);
         ticketRepository.save(ticketFromPHXtoAUS);
         ticketRepository.save(ticketFromAUStoORD);
         ticketRepository.save(ticketFromAUStoPHX);
         ticketRepository.save(ticketFromMDWtoAUS2);
         ticketRepository.save(ticketFromJFKtoDFW);
         ticketRepository.save(ticketFromORDtoJFK);


         // Assign tickets to flights
         ArrayList<Ticket> oneList = new ArrayList<>();
         oneList.add(ticketFromMDWtoAUS);
         oneList.add(ticketFromMDWtoAUS2);
         flightFromMDWtoAUS.setListOfTickets(oneList);

         ArrayList<Ticket> twoList = new ArrayList<>();
         twoList.add(ticketFromPHXtoAUS);
         flightFromPHXtoAUS.setListOfTickets(twoList);

         ArrayList<Ticket> threeList = new ArrayList<>();
         threeList.add(ticketFromAUStoORD);
         flightFromAUStoORD.setListOfTickets(threeList);

         ArrayList<Ticket> fourList = new ArrayList<>();
         fourList.add(ticketFromAUStoPHX);
         flightFromAUStoPHX.setListOfTickets(fourList);

         ArrayList<Ticket> fiveList = new ArrayList<>();
         fiveList.add(ticketFromJFKtoDFW);
         flightFromJFKtoDFW.setListOfTickets(fiveList);

         ArrayList<Ticket> sixList = new ArrayList<>();
         sixList.add(ticketFromORDtoJFK);
         flightFromORDtoJFK.setListOfTickets(sixList);

         // Assign new tickets to users / passengers
         ArrayList<Ticket> jeffTickets = new ArrayList<>();
         jeffTickets.add(ticketFromJFKtoDFW);
         jeff.setMyTicketsList(jeffTickets);

         // Assign tickets to users / passengers
         ArrayList<Ticket> dominiqueTickets = new ArrayList<>();
         dominiqueTickets.add(ticketFromMDWtoAUS);
         dominique.setMyTicketsList(dominiqueTickets);

         ArrayList<Ticket> kimTickets = new ArrayList<>();
         kimTickets.add(ticketFromPHXtoAUS);
         kim.setMyTicketsList(kimTickets);

         ArrayList<Ticket> jayTickets = new ArrayList<>();
         jayTickets.add(ticketFromAUStoORD);
         jay.setMyTicketsList(jayTickets);

         ArrayList<Ticket> deSheTickets = new ArrayList<>();
         deSheTickets.add(ticketFromAUStoPHX);
         deShe.setMyTicketsList(deSheTickets);
      }
   }
}