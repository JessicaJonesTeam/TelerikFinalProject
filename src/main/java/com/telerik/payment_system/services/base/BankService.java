package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;

import java.util.List;

public interface BankService {

    List<Bill> getAllNonPaymentBillsForSubscriber(String phoneNumber);
    Subscriber findByPhoneNumber(String phoneNumber);
}