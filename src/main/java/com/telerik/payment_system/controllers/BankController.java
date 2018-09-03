package com.telerik.payment_system.controllers;

import com.google.gson.Gson;
import com.telerik.payment_system.Utilities.JwtParser;
import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.viewModels.BillViewModel;
import com.telerik.payment_system.models.viewModels.SubscriberViewModel;
import com.telerik.payment_system.repositories.base.UserRepository;
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

    private final JwtParser jwtParser;

//    private final UserRepository userRepository;

    private final Gson gson;

    @Autowired
    public BankController(BankService bankService, JwtParser jwtParser, Gson gson) {
        this.bankService = bankService;
        this.jwtParser = jwtParser;
//        this.userRepository = userRepository;
        this.gson = gson;
    }

//has frontend
    @GetMapping("/bills")
    public @ResponseBody
    String getAllNonPaymentBill(HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getAllUnpaidBill(bankId));
    }

//has frontend
    @GetMapping("/bills/unpaid/{phoneNumber}")
    public @ResponseBody
    String getAllNonePaymentBillsBySubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getAllUnpaidBillsBySubscriber(bankId, phoneNumber));
    }

    @GetMapping("/bills/{phoneNumber}")
    public @ResponseBody
    String getAllPaymentsBySubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getAllPaymentsBySubscriber(phoneNumber,bankId));
    }

    @GetMapping("/subscribers")
    public @ResponseBody
    String getAllSubscribers(HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.listAllSubscribers(bankId));
    }

    @GetMapping("/subscribers/{phoneNumber}")
    public @ResponseBody
    String getSubscriberDetails(@PathVariable("phoneNumber") String phoneNumber, HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.findByPhoneNumber(bankId, phoneNumber));
    }

    @GetMapping("/subscribers/history/{phoneNumber}")
    public @ResponseBody
    String getHistoryBySubscriber(@PathVariable("phoneNumber") String phoneNumber, HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getHistoryBySubscriber(phoneNumber, bankId));
    }

    @GetMapping("/subscribers/average/{phoneNumber}/{timeInterval}")
    public Double averageAmount(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("timeInterval") List<String> timeInterval,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return bankService.averageAmount(timeInterval, phoneNumber, bankId);
    }

    @GetMapping("/subscribers/max/{phoneNumber}/{timeInterval}")
    public Double maxAmount(@PathVariable("phoneNumber") String phoneNumber,
                            @PathVariable("timeInterval") List<String> timeInterval,
                            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return bankService.maxAmount(timeInterval, phoneNumber, bankId);
    }

    @PostMapping("subscribers/pay/{phoneNumber}")
    public void payAllBillsBySubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        bankService.payAllPaymentsBySubscriber(phoneNumber, bankId);
    }
//has frontend
    @GetMapping ("subscribers/pay/{phoneNumber}/{billId}")
    public void payAllBillsById(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("billId") int billId,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        bankService.payBillById(billId, bankId, phoneNumber);
    }

    @GetMapping("subscribers/service/{phoneNumber}")
    public List<Service> getAllServices(@PathVariable("phoneNumber") String phoneNumber, HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return bankService.getAllServices(phoneNumber, bankId);
    }

    @GetMapping("subscribers/top10")
    public HashMap<Subscriber, Double> findTop10(HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return bankService.findTop10(bankId);
    }

}