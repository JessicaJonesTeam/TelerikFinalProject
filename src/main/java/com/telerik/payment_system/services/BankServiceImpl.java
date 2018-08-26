package com.telerik.payment_system.services;

import com.telerik.payment_system.Utilities.JwtParser;
import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.repositories.base.SubscriberRepository;
import com.telerik.payment_system.repositories.base.UserRepository;
import com.telerik.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final BillRepository billRepository;

    private final SubscriberRepository subscriberRepository;

    private final JwtParser jwtParser;

    private final UserRepository userRepository;

    @Autowired
    public BankServiceImpl(BillRepository billRepository,
                           SubscriberRepository subscriberRepository,
                           JwtParser jwtParser,
                           UserRepository userRepository) {
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
        this.jwtParser = jwtParser;
        this.userRepository = userRepository;
    }

    @Override
    public List<Bill> getAllNonPaymentBillsForSubscriber(String phoneNumber, HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();
        return billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(id, phoneNumber);
    }

    @Override
    public Subscriber findByPhoneNumber(String phoneNumber, HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();

        return subscriberRepository.getByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Bill> getHistoryBySubscriber(String phoneNumber, HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();

        return billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
    }

    @Override
    public Double averageAmount(String phoneNumber, HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();

        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
        double sum = 0;
        for (Bill bill : bills) {
            sum += bill.getAmount();
        }
        return sum / bills.size();
    }

    @Override
    public Double maxAmount(String phoneNumber, HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();

        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
        double max = 0;
        for (Bill bill : bills) {
            max = Math.max(max, bill.getAmount());
        }
        return max;
    }

    @Override
    public void payAllBillsBySubscriber(String phoneNumber, HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(id, phoneNumber);
        for (Bill bill : bills) {
            bill.setPaymentDate(new Date(System.currentTimeMillis()));
            this.billRepository.saveAndFlush(bill);
        }
    }

    @Override
    public List<com.telerik.payment_system.entities.Service> getAllServices(String phoneNumber, HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();

        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
        List<com.telerik.payment_system.entities.Service> services = new ArrayList<>();
        for (Bill bill : bills) {
            services.add(bill.getService());
        }
        return services;
    }

    @Override
    public HashMap<Subscriber, Double> findTop10(HttpServletRequest request) {
        //TODO: filter by bankId
        User bank = (User) this.userRepository.getByUsername(this.jwtParser.getUsernameFromToken(request));
        long id = bank.getId();

        HashMap<Subscriber, Double> top10 = new HashMap<>();
        List<Bill> bills = billRepository.findAll();
        for (Bill bill : bills) {
            if (!top10.containsKey(bill.getSubscriber())) {
                top10.put(bill.getSubscriber(), bill.getAmount());
            } else {
                double temp = top10.get(bill.getSubscriber());
                top10.put(bill.getSubscriber(), bill.getAmount() + temp);
            }
        }
        return top10;
    }


}
