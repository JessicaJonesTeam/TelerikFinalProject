package com.telerik.payment_system.controllers;

import com.telerik.payment_system.Utilities.JwtParser;
import com.telerik.payment_system.services.base.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/change_password")
public class ChagePasswordController {

    private final JwtParser jwtParser;
    private final AdminService adminService;

    public ChagePasswordController(JwtParser jwtParser, AdminService adminService) {
        this.jwtParser = jwtParser;
        this.adminService = adminService;
    }

    @PostMapping
    public void chagePassword(@RequestBody String password, HttpServletRequest request) {
        long userId = jwtParser.getBankIdByUsernameFromToken(request);
        adminService.changePassword(userId,password);
    }
}
