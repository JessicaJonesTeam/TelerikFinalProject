package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User getByUsername(String username);

    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(User user);

    void editUser(Long id, User user);

    void deleteUser(Long id);

   void createPayment(Bill billFeed);

}
