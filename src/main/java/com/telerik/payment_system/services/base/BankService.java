package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.models.viewModels.SubscriberViewModel;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public interface BankService {

    List<Bill> getAllUnpaidBill(long bankId);

    List<Bill> getAllUnpaidBillsBySubscriber( long bankId,String phoneNumber);

    Subscriber findByPhoneNumber(long bankId,String phoneNumber);

    List<Bill> getHistoryBySubscriber(String phoneNumber,long bankId);

    Double averageAmount (List <String> timeInterval, String phoneNumber, long bankId);

    Double maxAmount (List <String> timeInterval, String phoneNumber, long bankId);

    void payAllBillsBySubscriber(String phoneNumber,long bankId);

    void payAllBillsById(int billId, long bankId, String phoneNumber);

    List<Service> getAllServices (String phoneNumber,long bankId);

    HashMap<Subscriber, Double> findTop10(long bankId);


}
