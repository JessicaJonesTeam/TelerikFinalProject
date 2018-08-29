package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {


    List<User> getAllUsers();

    void createUser(User user);

    void editUser(long id, User user);

    void deleteUser( long id);

   void createPayment(Bill billFeed);

}
