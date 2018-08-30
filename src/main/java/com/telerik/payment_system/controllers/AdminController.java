package com.telerik.payment_system.controllers;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.viewModels.UserViewModel;
import com.telerik.payment_system.repositories.base.UserRepository;
import com.telerik.payment_system.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository, AdminService adminService) {
        this.adminService = adminService;
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public List<User> listUsers() {
        return adminService.getAllUsers();


    }

    //FOR TESTS ONLY
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id) {
        long userId = Long.parseLong(id);
        return this.userRepository.getById(userId);
    }

    @PostMapping("users/create")
    public void createUser(@RequestBody User user) {
//       TODO: Handle if user is null
        this.adminService.createUser(user);
    }

    @PutMapping("users/update/{id}")
    public void updateUser(@PathVariable("id") String userId, @RequestBody User user) {
//       TODO; handle if id doesnt exist
        long id = Long.parseLong(userId);
        adminService.editUser(id, user);
    }

    @DeleteMapping("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        //       TODO; handle if id doesnt exist
        long id = Long.parseLong(userId);
        adminService.deleteUser(id);
    }

    @PostMapping("bills/create/")
    public void createBill(@RequestBody Bill bill) {
        adminService.createPayment(bill);
    }
}
