package com.skylight.models;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name = "flights")
public class Flights {
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

   @Column
   private String origin;

   @Column
   private String destination;

   @Column
   private String distance;

   @Column
   private Float price;

   public Flights() {}

   public Flights(Long id, Long numberOfSeats, String airplane, String date, String time, String origin, String destination, String distance, Float price) {
      this.id = id;
      this.numberOfSeats = numberOfSeats;
      this.airplane = airplane;
      this.date = date;
      this.time = time;
      this.origin = origin;
      this.destination = destination;
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

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public String getDestination() {
      return destination;
   }

   public void setDestination(String destination) {
      this.destination = destination;
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
      return "Flights{" +
              "id=" + id +
              ", numberOfSeats=" + numberOfSeats +
              ", airplane='" + airplane + '\'' +
              ", date='" + date + '\'' +
              ", time='" + time + '\'' +
              ", origin='" + origin + '\'' +
              ", destination='" + destination + '\'' +
              ", distance='" + distance + '\'' +
              ", price='" + price + '\'' +
              '}';
   }
}
