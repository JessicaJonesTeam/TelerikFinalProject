package com.telerik.payment_system.controllers;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    public List<Bill> getAllNonePaymentBillsForSubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {
        return bankService.getAllNonPaymentBillsForSubscriber( phoneNumber,request);
    }

    @GetMapping("/subscribers/details/{phoneNumber}")
    public Subscriber getSubscriberDetails(@PathVariable("phoneNumber") String phoneNumber,HttpServletRequest request) {
        return bankService.findByPhoneNumber(phoneNumber,request);
    }

    @GetMapping("/subscribers/history/{phoneNumber}")
    public List<Bill> getHistoryBySubscriber(@PathVariable("phoneNumber") String phoneNumber,HttpServletRequest request) {
        return bankService.getHistoryBySubscriber(phoneNumber,request);
    }

    @GetMapping("/subscribers/average/{phoneNumber}")
    public Double averageAmount(@PathVariable("phoneNumber") String phoneNumber,HttpServletRequest request) {
        return bankService.averageAmount(phoneNumber,request);
    }

    @GetMapping("/subscribers/max/{phoneNumber}")
    public Double maxAmount(@PathVariable("phoneNumber") String phoneNumber,HttpServletRequest request) {
        return bankService.maxAmount(phoneNumber,request);
    }

    @PostMapping("subscribers/pay/{phoneNumber}")
    public void payAllBillsBySubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {
        bankService.payAllBillsBySubscriber( phoneNumber,request);
    }

    @GetMapping("subscribers/service/{phoneNumber}")
    public List<Service> getAllServices(@PathVariable("phoneNumber") String phoneNumber,HttpServletRequest request) {
        return bankService.getAllServices(phoneNumber,request);
    }

    @GetMapping("subscribers/top10")
    public HashMap<Subscriber, Double> findTop10(HttpServletRequest request) {
        return bankService.findTop10(request);
    }

}