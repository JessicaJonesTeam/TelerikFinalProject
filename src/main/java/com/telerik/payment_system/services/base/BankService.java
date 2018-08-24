package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface BankService {

    List<Bill> getAllNonPaymentBillsForSubscriber(String bankId, String phoneNumber);
    Subscriber findByPhoneNumber(String phoneNumber);
    List<Bill> getHistoryBySubscriber(String phoneNumber);
    Double averageAmount (String phoneNumber);
    Double maxAmount (String phoneNumber);
    void payAllBillsBySubscriber(String bankId, String phoneNumber);
    List<Service> getAllServices (String phoneNumber);
    HashMap<Subscriber, Double> findTop10();


}
