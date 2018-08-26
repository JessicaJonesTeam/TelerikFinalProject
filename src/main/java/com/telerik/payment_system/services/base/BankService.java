package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface BankService {

    List<Bill> getAllNonPaymentBillsForSubscriber( String phoneNumber,HttpServletRequest request);
    Subscriber findByPhoneNumber(String phoneNumber,HttpServletRequest request);
    List<Bill> getHistoryBySubscriber(String phoneNumber,HttpServletRequest request);
    Double averageAmount (String phoneNumber,HttpServletRequest request);
    Double maxAmount (String phoneNumber,HttpServletRequest request);
    void payAllBillsBySubscriber(String phoneNumber,HttpServletRequest request);
    List<Service> getAllServices (String phoneNumber,HttpServletRequest request);
    HashMap<Subscriber, Double> findTop10(HttpServletRequest request);


}
