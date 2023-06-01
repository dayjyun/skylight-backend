package com.skylight.models;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name = "tickets")
public class Ticket {
   @Column
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "flight_id")
   private Flight flight;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User passenger;

   public Ticket() {}

   public Ticket(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Flight getFlight() {
      return flight;
   }

   public void setFlight(Flight flight) {
      this.flight = flight;
   }

   public User getPassenger() {
      return passenger;
   }

   public void setPassenger(User passenger) {
      this.passenger = passenger;
   }

   @Override
   public String toString() {
      return "Tickets{" +
              "id=" + id +
              ", flight=" + flight +
              ", passenger=" + passenger +
              '}';
   }
}
