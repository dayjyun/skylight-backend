package com.skylight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.List;

@Entity
@Table(name = "airports")
public class Airport {
   @Column
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String name;

   @Column
   private String airportCode;

   @Column
   private String city;

   @Column
   private String state;

   @Column
   private String latitude;

   @Column
   private String longitude;

   @OneToMany(mappedBy = "originAirport", orphanRemoval = true)
   @LazyCollection(LazyCollectionOption.FALSE)
   @JsonIgnore
   private List<Flight> flightsFromAirportList;

   @OneToMany(mappedBy = "destinationAirport", orphanRemoval = true)
   @LazyCollection(LazyCollectionOption.FALSE)
   @JsonIgnore
   private List<Flight> flightsToAirportList;

   public Airport() {}

   public Airport(Long id, String name, String airportCode, String city, String state, String latitude, String longitude) {
      this.id = id;
      this.name = name;
      this.airportCode = airportCode;
      this.city = city;
      this.state = state;
      this.latitude = latitude;
      this.longitude = longitude;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAirportCode() {
      return airportCode;
   }

   public void setAirportCode(String airportCode) {
      this.airportCode = airportCode;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public List<Flight> getFlightsFromAirportList() {
      return flightsFromAirportList;
   }

   public void setFlightsFromAirportList(List<Flight> flightsFromAirportList) {
      this.flightsFromAirportList = flightsFromAirportList;
   }

   public List<Flight> getFlightsToAirportList() {
      return flightsToAirportList;
   }

   public void setFlightsToAirportList(List<Flight> flightsToAirportList) {
      this.flightsToAirportList = flightsToAirportList;
   }

   @Override
   public String toString() {
      return "Airport{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", airportCode='" + airportCode + '\'' +
              ", city='" + city + '\'' +
              ", state='" + state + '\'' +
              ", latitude='" + latitude + '\'' +
              ", longitude='" + longitude + '\'' +
              ", flightsFromAirportList=" + flightsFromAirportList +
              ", flightsToAirportList=" + flightsToAirportList +
              '}';
   }
}
