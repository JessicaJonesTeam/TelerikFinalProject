package com.telerik.payment_system.controllers;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{phoneNumber}")
    public List<Bill> getAllNonePaymentBillsForSubscriber(@PathVariable("phoneNumber") String phoneNumber) {
        return bankService.getAllNonPaymentBillsForSubscriber(phoneNumber);
    }

    @GetMapping("/subscribers/{phoneNumber}")
    public Subscriber getSubscriberDetails(@PathVariable("phoneNumber") String phoneNumber) {
        return bankService.findByPhoneNumber(phoneNumber);
    }




}
