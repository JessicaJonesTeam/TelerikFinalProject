package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDetails getByUsername(String username);

    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(User user);

    void editUser(long id, User user);

    void deleteUser( long id);

   void createPayment(Bill billFeed);

}
