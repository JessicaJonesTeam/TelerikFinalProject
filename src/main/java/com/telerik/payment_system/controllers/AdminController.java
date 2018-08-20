package com.telerik.payment_system.controllers;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.UserRepository;
import com.telerik.payment_system.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> listUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    @PostMapping("users/create/")
    public void createUser(@RequestBody User user) {
//       TODO: Handle if user is null
        this.userService.createUser(user);
    }

    @PutMapping("users/update/{id}")
    public void updateUser(@PathVariable("id") String userId, @RequestBody User user) {
//       TODO; handle if id doesnt exist
        long id = Long.parseLong(userId);
        userService.editUser(id, user);
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        //       TODO; handle if id doesnt exist
        long id = Long.parseLong(userId);
        userService.deleteUser(id);
    }

    @PostMapping("bills/create/")
    public void createBill(@RequestBody Bill bill) {
        userService.createPayment(bill);
    }
}
