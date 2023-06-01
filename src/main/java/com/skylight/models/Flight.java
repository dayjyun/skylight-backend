package com.skylight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {
   @Column
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private Long numberOfSeats;

   @Column
   private String airplane;

   @Column
   private String date;

   @Column
   private String time;

   @ManyToOne
   @JoinColumn(name = "origin_airport")
   private Airport originAirport;

   @ManyToOne
   @JoinColumn(name = "destination_airport")
   private Airport destinationAirport;

   // Pilot created flight / assigned to the flight
   @ManyToOne
   @JoinColumn(name = "pilot_id")
   @JsonIgnore
   private User pilot;

   // List of flights a passenger is scheduled to board
   @ManyToMany
   @JoinTable(name = "flight_passengers",
               joinColumns = @JoinColumn(name = "flight_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id"))
   @JsonIgnore
   private List<User> bookedFlightsList;

   @Column
   private String distance;

   @Column
   private Float price;

   public Flight() {}

   public Flight(Long id, Long numberOfSeats, String airplane, String date, String time, String distance, Float price) {
      this.id = id;
      this.numberOfSeats = numberOfSeats;
      this.airplane = airplane;
      this.date = date;
      this.time = time;
      this.distance = distance;
      this.price = price;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getNumberOfSeats() {
      return numberOfSeats;
   }

   public void setNumberOfSeats(Long numberOfSeats) {
      this.numberOfSeats = numberOfSeats;
   }

   public String getAirplane() {
      return airplane;
   }

   public void setAirplane(String airplane) {
      this.airplane = airplane;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public Airport getOriginAirport() {
      return originAirport;
   }

   public void setOriginAirport(Airport originAirport) {
      this.originAirport = originAirport;
   }

   public Airport getDestinationAirport() {
      return destinationAirport;
   }

   public void setDestinationAirport(Airport destinationAirport) {
      this.destinationAirport = destinationAirport;
   }

   public String getDistance() {
      return distance;
   }

   public void setDistance(String distance) {
      this.distance = distance;
   }

   public Float getPrice() {
      return price;
   }

   public void setPrice(Float price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "Flight{" +
              "id=" + id +
              ", numberOfSeats=" + numberOfSeats +
              ", airplane='" + airplane + '\'' +
              ", date='" + date + '\'' +
              ", time='" + time + '\'' +
              ", originAirport=" + originAirport +
              ", destinationAirport=" + destinationAirport +
              ", distance='" + distance + '\'' +
              ", price=" + price +
              '}';
   }
}