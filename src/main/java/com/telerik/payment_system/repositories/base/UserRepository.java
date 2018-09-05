package com.telerik.payment_system.repositories.base;

import com.telerik.payment_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails getByUsername(String username);
    User getById(Long id);
    List<User> getAllByEnabledIsTrue();


}
