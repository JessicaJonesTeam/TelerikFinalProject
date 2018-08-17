package com.telerik.payment_system.controllers;

import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
        public List<User> listUsers() {
            List<User> users = userRepository.findAll();

            return users;
    }
}
