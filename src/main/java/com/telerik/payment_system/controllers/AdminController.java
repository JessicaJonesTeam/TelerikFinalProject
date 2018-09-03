package com.telerik.payment_system.controllers;

import com.google.gson.Gson;
import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.bindingModels.BillRecordBindingModel;
import com.telerik.payment_system.models.bindingModels.UserBindingModel;
import com.telerik.payment_system.models.bindingModels.UserEditBindingModel;
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

//    private final UserRepository userRepository;

    private final Gson gson;

    @Autowired
    public AdminController(AdminService adminService, Gson gson) {
        this.adminService = adminService;
//        this.userRepository = userRepository;
        this.gson = gson;
    }

//has frontend
    @GetMapping("/users")
    public  @ResponseBody String  listUsers() {
         return this.gson.toJson(this.adminService.getAllUsers());
    }

//has frontend
    @GetMapping("/users/{id}")
    public @ResponseBody String getUserById(@PathVariable("id") String id) {
        long userId = Long.parseLong(id);
        return this.gson.toJson(this.adminService.getUserByID(userId));
    }

//has frontend
    @PostMapping("users/create")
    public void createUser(@RequestBody UserBindingModel userBindingModel) {
//       TODO: Handle if user is null
        this.adminService.createUser(userBindingModel);
    }

//has frontend
    @PostMapping("users/update")
    public void editUser(@RequestBody UserEditBindingModel userEditBindingModel) {
//       TODO; handle if id doesnt exist
        adminService.editUser(userEditBindingModel);
    }

//has frontend
    @PostMapping ("users/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId) {
        //       TODO; handle if id doesnt exist
        long id = Long.parseLong(userId);
        adminService.deleteUser(id);
    }

//has frontend
    @PostMapping("bills/create")
    public void createBill(@RequestBody BillRecordBindingModel recordBindingModel) {
        adminService.createPayment(recordBindingModel);
    }
}
