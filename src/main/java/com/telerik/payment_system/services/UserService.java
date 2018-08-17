package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.UserBindingModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getByUsername(String username);

    User saveUser(UserBindingModel userDTO);

    List<User> getAllUsers();

    User getUserById(Long id);

    User editUser(Long id, UserBindingModel userEditBindingModel);

    void deleteUser(String name);


}
