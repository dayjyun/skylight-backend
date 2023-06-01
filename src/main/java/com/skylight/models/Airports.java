package com.skylight.models;

public class Airports {
   private Long id;
   private String name;
   private String airportCode;
   private String city;
   private String state;
   private String latitude;
   private String longitude;

   public Airports() {}

   public Airports(Long id, String name, String airportCode, String city, String state, String latitude, String longitude) {
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

   @Override
   public String toString() {
      return "Airports{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", airportCode='" + airportCode + '\'' +
              ", city='" + city + '\'' +
              ", state='" + state + '\'' +
              ", latitude='" + latitude + '\'' +
              ", longitude='" + longitude + '\'' +
              '}';
   }
}
