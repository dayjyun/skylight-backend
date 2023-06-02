package com.skylight.seed;

import com.skylight.models.Airport;
import com.skylight.models.Flight;
import com.skylight.models.Ticket;
import com.skylight.models.User;
import com.skylight.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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

   @Override
   public void run(String... args) throws Exception {
      loadSeedData();
   }

   private void loadSeedData() {
      if (airportRepository.count() == 0 && flightRepository.count() == 0 && ticketRepository.count() == 0 && userRepository.count() == 0) {

         // Create Users
         // Users: Admins/Pilots
         User aUser = new User(1L, "a", "a@email.com", "pw", true);
         User bUser = new User(2L, "b", "b@email.com", "pw", true);
         User cUser = new User(3L, "c", "c@email.com", "pw", true);

         // Users: Passengers
         User dUser = new User(4L, "d", "d@email.com", "pw", false);
         User eUser = new User(5L, "e", "e@email.com", "pw", false);
         User fUser = new User(6L, "f", "f@email.com", "pw", false);
         User gUser = new User(7L, "g", "g@email.com", "pw", false);
         User hUser = new User(8L, "h", "h@email.com", "pw", false);

         // Save users
         userRepository.save(aUser);
         userRepository.save(bUser);
         userRepository.save(cUser);
         userRepository.save(dUser);
         userRepository.save(eUser);
         userRepository.save(fUser);
         userRepository.save(gUser);
         userRepository.save(hUser);

         // Create Airports
         Airport midwayAirport =  new Airport(1L, "Midway", "MDW", "Chicago", "IL", "41.7865 N",  "87.6298 W");
         Airport ohareAirport = new Airport(2L, "O'Hare", "OHX", "Chicago", "IL", "41.8781 N",  "87.6298 W");
         Airport austinAirport =  new Airport(3L, "Austin", "AUS", "Austin", "TX", "30.2672 N",  "97.7431 W");
         Airport phoenixAirport = new Airport(4L, "Phoenix", "PHX", "Phoenix", "AZ", "33.4484 N",  "112.0740 W");
         
         // Save airports
         airportRepository.save(midwayAirport);
         airportRepository.save(ohareAirport);
         airportRepository.save(austinAirport);
         airportRepository.save(phoenixAirport);


         // Create flights
         Flight flightFromMDWtoAUS = new Flight(1L, "Boeing 737", "07-01-2023", "0900", 0,"1200",  1124);
         Flight flightFromPHXtoAUS = new Flight(2L, "Boeing 737", "07-01-2023", "0900", 0,"1100",  885);
         Flight flightFromAUStoORD = new Flight(3L, "Boeing 737", "07-01-2023", "0900", 0,"1200",  978);
         Flight flightFromAUStoPHX = new Flight(4L, "Boeing 737", "07-01-2023", "1300", 0,"1530",  1133);

         // Assign origin and destination airports to flights
         // Assign pilot to flights
         flightFromMDWtoAUS.setOriginAirport(midwayAirport); // 1
         flightFromMDWtoAUS.setDestinationAirport(austinAirport); // 3
         flightFromMDWtoAUS.setPilot(aUser); // 1
         // Add flight to flight list for pilot 1
         ArrayList<Flight> originMDWtoAUS = new ArrayList<>();
         originMDWtoAUS.add(flightFromMDWtoAUS);
         aUser.setMyFlightsList(originMDWtoAUS);

         // Assign origin and destination airports to flights
         // Assign pilot to flights
         flightFromPHXtoAUS.setOriginAirport(phoenixAirport); // 4
         flightFromPHXtoAUS.setDestinationAirport(austinAirport); // 3
         flightFromPHXtoAUS.setPilot(bUser); // 2
         // Add flight to flight list for pilot 2
         ArrayList<Flight> originPHXtoAUS = new ArrayList<>();
         originPHXtoAUS.add(flightFromPHXtoAUS);
         bUser.setMyFlightsList(originPHXtoAUS);

         // Assign origin and destination airports to flights
         // Assign pilot to flights
         flightFromAUStoORD.setOriginAirport(austinAirport); // 3
         flightFromAUStoORD.setDestinationAirport(ohareAirport); // 2
         flightFromAUStoORD.setPilot(cUser); // 3
         // Add flight to flight list for pilot 3
         ArrayList<Flight> originAUStoORD = new ArrayList<>();
         originAUStoORD.add(flightFromAUStoORD);
         cUser.setMyFlightsList(originAUStoORD);

         // Assign origin and destination airports to flights
         // Assign pilot to flights
         flightFromAUStoPHX.setOriginAirport(phoenixAirport); // 3
         flightFromAUStoPHX.setDestinationAirport(phoenixAirport); // 4
         flightFromAUStoPHX.setPilot(aUser); // 1
         // Add flight to flight list for pilot 1
         ArrayList<Flight> originAUStoPHX = new ArrayList<>();
         originAUStoPHX.add(flightFromAUStoORD);
         aUser.setMyFlightsList(originAUStoPHX);

         // Assign origin and destination airports to flights
         // Assign pilot to flights
         flightFromAUStoORD.setOriginAirport(austinAirport); // 3
         flightFromAUStoORD.setDestinationAirport(ohareAirport); // 2
         flightFromAUStoORD.setPilot(cUser); // 3
         // Add flight to flight list for pilot 3
         ArrayList<Flight> destinationAUS = new ArrayList<>();
         destinationAUS.add(flightFromAUStoORD);
         cUser.setMyFlightsList(destinationAUS);

         // Assign origin and destination airports to flights
         // Assign pilot to flights
//         flightFromAUStoPHX.setOriginAirport(phoenixAirport); // 3
         flightFromAUStoPHX.setDestinationAirport(phoenixAirport); // 4
         flightFromAUStoPHX.setPilot(aUser); // 1
         // Add flight to flight list for pilot 1
         ArrayList<Flight> destinationPHX = new ArrayList<>();
         destinationPHX.add(flightFromAUStoPHX);
         aUser.setMyFlightsList(destinationPHX);

         // Set arrival array lists for airports
         // Set departures array list for airports

         // Flight 1
         // Add origin flight to airport 1
         ArrayList<Flight> originMDWAirport = new ArrayList<>();
         originMDWAirport.add(flightFromMDWtoAUS);
         midwayAirport.setDepartingFlightsList(originMDWAirport);

         // Add destination flight to airport 3
         ArrayList<Flight> destinationAUSAirport = new ArrayList<>();
         destinationAUSAirport.add(flightFromAUStoORD);
         austinAirport.setArrivingFlightsList(destinationAUSAirport);

         // Flight 2
         // Add origin flight to airport 4
         ArrayList<Flight> originPHXAirport = new ArrayList<>();
         originPHXAirport.add(flightFromPHXtoAUS);
         phoenixAirport.setDepartingFlightsList(originPHXAirport);

         // Add destination flight to airport 3
         ArrayList<Flight> destinationORDAirport = new ArrayList<>();
         destinationORDAirport.add(flightFromAUStoPHX);
         ohareAirport.setArrivingFlightsList(destinationORDAirport);

         //  Flight 3
         // Add origin flight to airport 3
         ArrayList<Flight> originAUSAirport = new ArrayList<>();
         originAUSAirport.add(flightFromAUStoORD);
         austinAirport.setDepartingFlightsList(originAUSAirport);

         // Add destination flight to airport 4
         ArrayList<Flight> destinationPHXAirport = new ArrayList<>();
         destinationPHXAirport.add(flightFromAUStoPHX);
         phoenixAirport.setArrivingFlightsList(destinationPHXAirport);

         // Flight 4
         // Add origin flight to airport 3
         ArrayList<Flight> originORDAirport = new ArrayList<>();
         originORDAirport.add(flightFromAUStoORD);
         ohareAirport.setDepartingFlightsList(originORDAirport);

         // Add destination flight to airport 4
         destinationAUSAirport.add(flightFromAUStoPHX);
         austinAirport.setArrivingFlightsList(destinationAUSAirport);

         // Save flight details
         flightRepository.save(flightFromMDWtoAUS);
         flightRepository.save(flightFromPHXtoAUS);
         flightRepository.save(flightFromAUStoORD);
         flightRepository.save(flightFromAUStoPHX);

         // Create tickets
         Ticket oneT = new Ticket(1L,  flightFromMDWtoAUS, dUser);
         Ticket twoT = new Ticket(2L, flightFromPHXtoAUS, eUser);
         Ticket threeT = new Ticket(3L, flightFromAUStoORD, fUser);
         Ticket fourT = new Ticket(4L, flightFromAUStoPHX, gUser);

         // Save ticket details
         ticketRepository.save(oneT);
         ticketRepository.save(twoT);
         ticketRepository.save(threeT);
         ticketRepository.save(fourT);

         // Assign tickets to flights
         ArrayList<Ticket> oneList = new ArrayList<>();
         oneList.add(oneT);
         flightFromMDWtoAUS.setListOfTickets(oneList);

         ArrayList<Ticket> twoList = new ArrayList<>();
         twoList.add(twoT);
         flightFromPHXtoAUS.setListOfTickets(twoList);

         ArrayList<Ticket> threeList = new ArrayList<>();
         threeList.add(threeT);
         flightFromAUStoORD.setListOfTickets(threeList);

         ArrayList<Ticket> fourList = new ArrayList<>();
         fourList.add(fourT);
         flightFromAUStoPHX.setListOfTickets(fourList);

         // Assign tickets to users / passengers
         ArrayList<Ticket> dUserTickets = new ArrayList<>();
         dUserTickets.add(oneT);
         dUser.setMyTicketsList(dUserTickets);

         ArrayList<Ticket> eUserTickets = new ArrayList<>();
         eUserTickets.add(twoT);
         eUser.setMyTicketsList(eUserTickets);

         ArrayList<Ticket> fUserTickets = new ArrayList<>();
         fUserTickets.add(threeT);
         fUser.setMyTicketsList(fUserTickets);

         ArrayList<Ticket> gUserTickets = new ArrayList<>();
         gUserTickets.add(fourT);
         gUser.setMyTicketsList(gUserTickets);


      }
   }
}
