package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface BankService {

    List<Bill> getAllNonPaymentBillsForSubscriber(String phoneNumber);

    /**
     * bla
     * @param phoneNumber
     * @return
     */
    Subscriber findByPhoneNumber(String phoneNumber);
    List<Bill> getHistoryBySubscriber(String phoneNumber);
    Double averageAmount (String phoneNumber);
    Double maxAmount (String phoneNumber);
    void payAllBillsBySubscriber(String phoneNumber);
    List<Service> getAllServices (String phoneNumber);
//    HashMap<String, Double> findTop10 ();
    HashMap<Subscriber, Double> findTop10();

    List<Bill> getNon(Date startDate, Date endDate, String phoneNumber);
}
