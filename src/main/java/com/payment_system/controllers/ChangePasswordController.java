package com.payment_system.controllers;

import com.payment_system.Utilities.JwtParser;
import com.payment_system.models.bindingModels.ChangePassword;
import com.payment_system.services.base.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/change_password")
public class ChangePasswordController {

    private final JwtParser jwtParser;
    private final AdminService adminService;

    public ChangePasswordController(JwtParser jwtParser, AdminService adminService) {
        this.jwtParser = jwtParser;
        this.adminService = adminService;
    }

    @PostMapping
    public void changePassword(@RequestBody ChangePassword changePassword, HttpServletRequest request) {
        long userId = jwtParser.getBankIdByUsernameFromToken(request);
        adminService.changePassword(userId,changePassword);
    }
}
