package com.telerik.payment_system.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankServiceImpl implements BankService {

    private final BillRepository billRepository;

    @Autowired
    public BankServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }


    @Override

    public List<Bill> getAllNonePaymentBillsForSubscriber(String phoneNumber) {
        return billRepository.getAllBySubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(phoneNumber);
    }
}
