package com.telerik.payment_system.repositories;

import com.telerik.payment_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);

    User getById(Long id);

}
