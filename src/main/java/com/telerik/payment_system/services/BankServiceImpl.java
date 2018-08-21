package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.repositories.BillRepository;
import com.telerik.payment_system.repositories.ServiceRepository;
import com.telerik.payment_system.repositories.SubscriberRepository;
import com.telerik.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final BillRepository billRepository;
    private final SubscriberRepository subscriberRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public BankServiceImpl(BillRepository billRepository, SubscriberRepository subscriberRepository, ServiceRepository serviceRepository) {
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
        this.serviceRepository = serviceRepository;
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
            bill.setPaymentDate(new Date());
        }
    }


}
