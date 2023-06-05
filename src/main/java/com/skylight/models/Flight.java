package com.skylight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {
   @Column
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String airplane;

   @Column
   @NotNull(message = "Date is required")
   private String date;

   @Column
   @NotNull(message = "Departure time is required")
   private String departureTime;

   @Column
   @NotNull(message = "Arrival time is required")
   private String arrivalTime;

   @Column
   private Integer layoverTime;

   @Column
   private Integer distanceMiles;

   @Column
   private Double price;

   // Many flights One origin airport
   @ManyToOne
   @JoinColumn(name = "origin_airport")
   private Airport originAirport;

   // Many flights One destination airport
   @ManyToOne
   @JoinColumn(name = "destination_airport")
   private Airport destinationAirport;

   // Pilot created flight / assigned to the flight
   // Many flights One pilot
   @ManyToOne
   @JoinColumn(name = "user_id")
   @JsonIgnore
   private User pilot;

   // One flight Many passengers
   @OneToMany(mappedBy = "flight", orphanRemoval = true)
   @LazyCollection(LazyCollectionOption.FALSE)
   @JsonIgnore
   private List<Ticket> listOfTickets;

   public Flight() {}

   public Flight(Long id, String airplane, String date, Airport destinationAirport, String departureTime,
                 Integer layoverTime,
                 String arrivalTime,
                 Integer distanceMiles) {
      this.id = id;
      this.airplane = airplane;
      // MM-DD-YYYY
      this.date = date;
      this.destinationAirport = destinationAirport;
      // 24-Hour format
      this.departureTime = departureTime;
      this.arrivalTime = arrivalTime;
      // Waiting period is 60 minutes minimum
      this.layoverTime = layoverTime + 60;
      this.distanceMiles = distanceMiles;
      // Price is 30 cents per mile
      this.price = distanceMiles * 0.30;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public String getDepartureTime() {
      return departureTime;
   }

   public void setDepartureTime(String departureTime) {
      this.departureTime = departureTime;
   }

   public String getArrivalTime() {
      return arrivalTime;
   }

   public void setArrivalTime(String arrivalTime) {
      this.arrivalTime = arrivalTime;
   }

   public int getLayoverTime() {
      return layoverTime;
   }

   public void setLayoverTime(int layoverTime) {
      this.layoverTime = layoverTime;
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

   public Integer getDistance() {
      return distanceMiles;
   }

   public void setDistance(Integer distance) {
      this.distanceMiles = distance;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   // Relationships
   public User getPilot() {
      return pilot;
   }

   public void setPilot(User pilot) {
      this.pilot = pilot;
   }

   public List<Ticket> getListOfTickets() {
      return listOfTickets;
   }

   public void setListOfTickets(List<Ticket> listOfTickets) {
      this.listOfTickets = listOfTickets;
   }

   @Override
   public String toString() {
      return "Flight{" +
              "id=" + id +
              ", airplane='" + airplane + '\'' +
              ", date='" + date + '\'' +
              ", departureTime='" + departureTime + '\'' +
              ", arrivalTime='" + arrivalTime + '\'' +
              ", originAirport=" + originAirport +
              ", destinationAirport=" + destinationAirport +
              ", pilot=" + pilot +
              ", distanceMiles='" + distanceMiles + '\'' +
              ", price=" + price +
              '}';
   }
}
