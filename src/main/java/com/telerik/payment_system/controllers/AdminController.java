package com.telerik.payment_system.controllers;

import com.google.gson.Gson;
import com.telerik.payment_system.Utilities.JwtParser;
import com.telerik.payment_system.models.bindingModels.BillRecordBindingModel;
import com.telerik.payment_system.models.bindingModels.UserBindingModel;
import com.telerik.payment_system.models.bindingModels.UserEditBindingModel;
import com.telerik.payment_system.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;


    private final Gson gson;


    @Autowired
    public AdminController(AdminService adminService, Gson gson) {
        this.adminService = adminService;
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
    @PutMapping("users/update")
    public void editUser(@RequestBody UserEditBindingModel userEditBindingModel) {
//       TODO; handle if id doesnt exist
        adminService.editUser(userEditBindingModel);
    }

//has frontend
    @DeleteMapping ("users/delete/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        //       TODO; handle if id doesnt exist
        adminService.deleteUser(username);
    }


//has frontend
    @PostMapping("bills/create")
    public void createBill(@RequestBody BillRecordBindingModel recordBindingModel) {
        adminService.createPayment(recordBindingModel);
    }
}
