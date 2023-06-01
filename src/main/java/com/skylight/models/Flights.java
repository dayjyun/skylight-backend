package com.skylight.models;

public class Flights {
   private Long id;
   private Long numberOfSeats;
   private String airplane;
   private String date;
   private String time;
   private String origin;
   private String destination;
   private String distance;
   private String price;

   public Flights() {}

   public Flights(Long id, Long numberOfSeats, String airplane, String date, String time, String origin, String destination, String distance, String price) {
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

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
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
