package com.payment_system.controllers;

import com.google.gson.Gson;
import com.payment_system.Utilities.JwtParser;

import com.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    private final JwtParser jwtParser;


    private final Gson gson;

    @Autowired
    public BankController(BankService bankService, JwtParser jwtParser, Gson gson) {
        this.bankService = bankService;
        this.jwtParser = jwtParser;
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

    //has frontend
    @GetMapping("/bills/{phoneNumber}")
    public @ResponseBody
    String getAllPaymentsBySubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getAllPaymentsBySubscriber(phoneNumber, bankId));
    }

    //has frontend
    @GetMapping("/bills/history")
    public @ResponseBody
    String getHistoryByBankID( HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getHistoryByBankID(bankId));
    }

    //has frontend
    @GetMapping("/subscribers")
    public @ResponseBody
    String getAllSubscribers(HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.listAllSubscribers(bankId));
    }

    //has frontend
    @GetMapping("/subscribers/{phoneNumber}")
    public @ResponseBody
    String getSubscriberDetails(@PathVariable("phoneNumber") String phoneNumber, HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.findByPhoneNumber(bankId, phoneNumber));
    }

    //has frontend
    @GetMapping("/subscribers/history/{phoneNumber}")
    public @ResponseBody
    String getHistoryBySubscriber(@PathVariable("phoneNumber") String phoneNumber, HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getHistoryBySubscriber(phoneNumber, bankId));
    }


    //has frontend
    @GetMapping("/subscribers/average/{phoneNumber}/{timeInterval}")
    public @ResponseBody
    String averageAmount(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("timeInterval") List<String> interval,
            HttpServletRequest request) throws ParseException {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.averageAmount(interval, phoneNumber, bankId));
    }

    //has frontend
    @GetMapping("/subscribers/max/{phoneNumber}/{timeInterval}")
    public @ResponseBody
    String maxAmount(@PathVariable("phoneNumber") String phoneNumber,
                     @PathVariable("timeInterval") List<String> timeInterval,
                     HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.maxAmount(timeInterval, phoneNumber, bankId));
    }

    //has frontend
    @GetMapping("subscribers/pay/{phoneNumber}")
    public void payAllBillsBySubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        bankService.payAllPaymentsBySubscriber(phoneNumber, bankId);
    }

    //has frontend
    @GetMapping("subscribers/pay/{phoneNumber}/{billId}")
    public void payBillById(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("billId") int billId,
            HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        bankService.payBillById(billId, bankId, phoneNumber);
    }

//    @GetMapping("subscribers/services/{phoneNumber}")
//    public @ResponseBody
//    String getAllServices(@PathVariable("phoneNumber") String phoneNumber, HttpServletRequest request) {
//
//        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
//        return this.gson.toJson(bankService.getAllServices(phoneNumber, bankId));
//    }

    //has frontend
    @GetMapping("/bills/recent")
    public @ResponseBody
    String get10MostRecentPayments(HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.get10RecentPayments(bankId));
    }

    //has frontend
    @GetMapping("/subscribers/top")
    public @ResponseBody
    String get10SubscribersBiggestAmountPaid(HttpServletRequest request) {

        long bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return this.gson.toJson(bankService.getFirst10SubscribersByTotalPaymentAmount(bankId));
    }

}