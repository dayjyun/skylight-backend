package com.skylight.repositories;
import com.skylight.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   // register
   boolean existsByEmail(String email);

   // login
   User findUserByEmail(String email);

}
