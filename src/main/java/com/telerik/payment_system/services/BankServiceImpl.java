package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.models.viewModels.SubscriberViewModel;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.repositories.base.SubscriberRepository;
import com.telerik.payment_system.services.base.BankService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final BillRepository billRepository;

    private final SubscriberRepository subscriberRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BankServiceImpl(BillRepository billRepository,
                           SubscriberRepository subscriberRepository,
                           ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Bill> getAllNonPaymentBillsForSubscriber(long bankId,String phoneNumber) {
//         CHECKED: working

        return billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(bankId, phoneNumber);
    }

    @Override
    public SubscriberViewModel findByPhoneNumber(long bankId,String phoneNumber) {
//         CHECKED: working

        return this.modelMapper.map(subscriberRepository.getByBank_IdAndPhoneNumber(bankId,phoneNumber),SubscriberViewModel.class);
    }

    @Override
    public List<Bill> getHistoryBySubscriber(String phoneNumber,long bankId) {

        return billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId, phoneNumber);
    }

    @Override
    public Double averageAmount(String phoneNumber,long bankId) {

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId, phoneNumber);
        double sum = 0;
        for (Bill bill : bills) {
            sum += bill.getAmount();
        }
        return sum / bills.size();
    }

    @Override
    public Double maxAmount(String phoneNumber,long bankId) {

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId,phoneNumber);
        double max = 0;
        for (Bill bill : bills) {
            max = Math.max(max, bill.getAmount());
        }
        return max;
    }

    @Override
    public void payAllBillsBySubscriber(String phoneNumber,long bankId) {

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(bankId, phoneNumber);
        for (Bill bill : bills) {
            bill.setPaymentDate(new Date(System.currentTimeMillis()));
            this.billRepository.saveAndFlush(bill);
        }
    }

    @Override
    public List<com.telerik.payment_system.entities.Service> getAllServices(String phoneNumber,long bankId) {
        //TODO: filter by bankId

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId, phoneNumber);
        List<com.telerik.payment_system.entities.Service> services = new ArrayList<>();
        for (Bill bill : bills) {
            services.add(bill.getService());
        }
        return services;
    }

    @Override
    public HashMap<Subscriber, Double> findTop10(long bankId) {
        //TODO: filter by bankId

        HashMap<Subscriber, Double> top10 = new HashMap<>();
        List<Bill> bills = billRepository.getAllBySubscriber_Bank_Id(bankId);
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
