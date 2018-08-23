package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getByUsername(String username);

    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(User user);

    void editUser(String id, User user);

    void deleteUser(String id);

   void createPayment(Bill billFeed);

}
