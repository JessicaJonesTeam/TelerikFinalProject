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

    private final UserService userService;

    @Autowired
    public AdminController( UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("users/create")
    public void createUser(@RequestBody User user) {
//       TODO: Handle if user is null
        this.userService.createUser(user);
    }

    @PutMapping("users/update/{id}")
    public void updateUser(@PathVariable("id") String userId, @RequestBody User user) {
//       TODO; handle if id doesnt exist

        userService.editUser(userId, user);
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        //       TODO; handle if id doesnt exist
        userService.deleteUser(userId);
    }

    @PostMapping("bills/create/")
    public void createBill(@RequestBody Bill bill) {
        userService.createPayment(bill);
    }
}
