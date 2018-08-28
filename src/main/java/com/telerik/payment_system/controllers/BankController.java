package com.telerik.payment_system.controllers;

import com.telerik.payment_system.Utilities.JwtParser;
import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.entities.User;
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
    private final UserRepository userRepository;

    @Autowired
    public BankController(BankService bankService, JwtParser jwtParser, UserRepository userRepository) {
        this.bankService = bankService;
        this.jwtParser = jwtParser;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<Bill> getAllNonPaymentBill(HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user = (User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankService.getAllNonPaymentBill(bankId);
    }

    @GetMapping("/{phoneNumber}")
    public List<Bill> getAllNonePaymentBillsForSubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankService.getAllNonPaymentBillsForSubscriber(bankId, phoneNumber);
    }

    @GetMapping("/subscribers/details/{phoneNumber}")
    public SubscriberViewModel getSubscriberDetails(@PathVariable("phoneNumber") String phoneNumber, HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();

        return bankService.findByPhoneNumber(bankId,phoneNumber);
    }

    @GetMapping("/subscribers/history/{phoneNumber}")
    public List<Bill> getHistoryBySubscriber(@PathVariable("phoneNumber") String phoneNumber,HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankService.getHistoryBySubscriber(phoneNumber,bankId);
    }

    @GetMapping("/subscribers/average/{phoneNumber}/{timeInterval}")
    public Double averageAmount(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("timeInterval") List<String> timeInterval,
            HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankService.averageAmount(timeInterval, phoneNumber, bankId);
    }

    @GetMapping("/subscribers/max/{phoneNumber}/{timeInterval}")
    public Double maxAmount(@PathVariable("phoneNumber") String phoneNumber,
                            @PathVariable("timeInterval") List<String> timeInterval,
                            HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankService.maxAmount(timeInterval,phoneNumber,bankId);
    }

    @PostMapping("subscribers/pay/{phoneNumber}")
    public void payAllBillsBySubscriber(
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        bankService.payAllBillsBySubscriber( phoneNumber,bankId);
    }

    @PostMapping("subscribers/pay/{phoneNumber}/{billId}")
    public void payAllBillsById(
            @PathVariable("phoneNumber") String phoneNumber,
            @PathVariable("billId") int billId,
            HttpServletRequest request){

        String username = this.jwtParser.getUsernameFromToken(request);
        User user = (User) this.userRepository.getByUsername(username);
        long bankId = user.getId();

        bankService.payAllBillsById(billId,bankId,phoneNumber);
    }

    @GetMapping("subscribers/service/{phoneNumber}")
    public List<Service> getAllServices(@PathVariable("phoneNumber") String phoneNumber,HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankService.getAllServices(phoneNumber,bankId);
    }

    @GetMapping("subscribers/top10")
    public HashMap<Subscriber, Double> findTop10(HttpServletRequest request) {
        String username = this.jwtParser.getUsernameFromToken(request);
        User user =(User)this.userRepository.getByUsername(username);
        long bankId = user.getId();
        return bankService.findTop10(bankId);
    }

}