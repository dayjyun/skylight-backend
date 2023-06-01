package com.skylight.models;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Table(name = "users")
public class Users {
   @Column
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column
   private String name;

   @Column
   private String email;

   @Column
   private String password;

   @Column
   private boolean isAdmin;

   public Users() {}

   public Users(Long id, String name, String email, String password, boolean isAdmin) {
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

   @Override
   public String toString() {
      return "Users{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", isAdmin=" + isAdmin +
              '}';
   }
}
