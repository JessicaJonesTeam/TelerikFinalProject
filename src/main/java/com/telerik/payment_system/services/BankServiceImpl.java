package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.repositories.BillRepository;
import com.telerik.payment_system.repositories.ServiceRepository;
import com.telerik.payment_system.repositories.SubscriberRepository;
import com.telerik.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final BillRepository billRepository;
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public BankServiceImpl(BillRepository billRepository, SubscriberRepository subscriberRepository, ServiceRepository serviceRepository) {
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override

    public List<Bill> getAllNonPaymentBillsForSubscriber(String phoneNumber) {
        return billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(phoneNumber);
    }

    @Override
    public Subscriber findByPhoneNumber(String phoneNumber) {
        return subscriberRepository.getByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Bill> getHistoryBySubscriber(String phoneNumber) {
        return billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
    }

    @Override
    public Double averageAmount(String phoneNumber) {
        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
        double sum = 0;
        for (Bill bill : bills) {
            sum += bill.getAmount();
        }
        return sum / bills.size();
    }

    @Override
    public Double maxAmount(String phoneNumber) {
        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
        double max = 0;
        for (Bill bill : bills) {
            max = Math.max(max, bill.getAmount());
        }
        return max;
    }

    @Override
    public void payAllBillsBySubscriber(String phoneNumber) {
        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(phoneNumber);
        for (Bill bill : bills) {
            bill.setPaymentDate(new Date(System.currentTimeMillis()));
        }
    }

    @Override
    public List<com.telerik.payment_system.entities.Service> getAllServices(String phoneNumber) {
        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(phoneNumber);
        List<com.telerik.payment_system.entities.Service> services = new ArrayList<>();
        for (Bill bill : bills) {
            services.add(bill.getService());
        }
        return services;
    }

    @Override
    public HashMap<Subscriber, Double> findTop10() {

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
