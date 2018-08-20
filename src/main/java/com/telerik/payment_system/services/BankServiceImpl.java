package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.repositories.BillRepository;
import com.telerik.payment_system.repositories.SubscriberRepository;
import com.telerik.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankServiceImpl implements BankService {

    private final BillRepository billRepository;
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public BankServiceImpl(BillRepository billRepository, SubscriberRepository subscriberRepository) {
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override

    public List<Bill> getAllNonPaymentBillsForSubscriber(String phoneNumber) {
        return billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(phoneNumber);
    }

    @Override
    public Subscriber findByPhoneNumber(String phoneNumber) {
        return subscriberRepository.findByPhoneNumber(phoneNumber);
    }



}
