package com.skylight.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
   @Column
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String name;

   @Column(unique = true)
   private String email;

   @Column
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   private String password;

   // Only admins/pilots can create flights
   @Column
   private boolean isAdmin;

   // List of flights a pilot is schedule to fly
   @OneToMany(mappedBy = "pilot", orphanRemoval = true)
   @LazyCollection(LazyCollectionOption.FALSE)
   @JsonIgnore
   private List<Flight> myFlightsList;

//   // List of flights a passenger is scheduled to board
//   @ManyToMany(mappedBy = "bookedFlightsList")
//   @JsonIgnore
//   private List<Flight> myBookedFlightsList;

   // List of tickets for flights a passenger booked
   @OneToMany(mappedBy = "passenger", orphanRemoval = true)
   @LazyCollection(LazyCollectionOption.FALSE)
   @JsonIgnore
   private List<Ticket> myTicketsList;

   public User() {}

   public User(Long id, String name, String email, String password, boolean isAdmin) {
      this.id = id;
      this.name = name;
      this.email = email;
      this.password = password;
      this.isAdmin = isAdmin;
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

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isAdmin() {
      return isAdmin;
   }

   public void setAdmin(boolean admin) {
      isAdmin = admin;
   }

   // Relationships
   public List<Flight> getMyFlightsList() {
      return myFlightsList;
   }

   public void setMyFlightsList(List<Flight> myFlightsList) {
      this.myFlightsList = myFlightsList;
   }

//   public List<Flight> getMyBookedFlightsList() {
//      return myBookedFlightsList;
//   }
//
//   public void setMyBookedFlightsList(List<Flight> myBookedFlightsList) {
//      this.myBookedFlightsList = myBookedFlightsList;
//   }


   public List<Ticket> getMyTicketsList() {
      return myTicketsList;
   }

   public void setMyTicketsList(List<Ticket> myTicketsList) {
      this.myTicketsList = myTicketsList;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", isAdmin=" + isAdmin +
              ", myFlightsList=" + myFlightsList +
//              ", myBookedFlightsList=" + myBookedFlightsList +
              '}';
   }
}
