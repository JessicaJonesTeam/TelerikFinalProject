package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;

import java.util.List;

public interface BankService {

    List<Bill> getAllNonePaymentBillsForSubscriber(String phoneNumber);
}
