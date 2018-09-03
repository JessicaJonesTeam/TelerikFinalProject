package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.models.viewModels.BillViewModel;
import com.telerik.payment_system.models.viewModels.SubscriberViewModel;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.repositories.base.SubscriberRepository;
import com.telerik.payment_system.services.base.BankService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<BillViewModel> getAllUnpaidBill(long bankId) {
        List<Bill> bills = this.billRepository.getAllBySubscriber_Bank_IdAndPaymentDateIsNullOrderByAmount(bankId);
        List<BillViewModel> billViewModels = new ArrayList<>();
        mapBillToViewModel(bills, billViewModels);
        return billViewModels;
    }

    @Override
    public List<BillViewModel> getAllUnpaidBillsBySubscriber(long bankId, String phoneNumber) {
        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(bankId, phoneNumber);
        List<BillViewModel> billViewModels = new ArrayList<>();
        mapBillToViewModel(bills, billViewModels);
        return billViewModels;
    }


    @Override
    public SubscriberViewModel findByPhoneNumber(long bankId, String phoneNumber) {
//         CHECKED: working
        Subscriber subscriber = subscriberRepository.getByBank_IdAndPhoneNumber(bankId, phoneNumber);
        SubscriberViewModel subscriberViewModel = new SubscriberViewModel();
        subscriberViewModel.setId(subscriber.getId());
        subscriberViewModel.setFullName(subscriber.getFirstName()+" "+subscriber.getLastName());
        subscriberViewModel.setEgn(subscriber.getEgn());
        subscriberViewModel.setPhoneNumber(subscriber.getPhoneNumber());
        List<BillViewModel> billViewModels= new ArrayList<>();
//        List<Bill> bills = billRepository.getAllBySubscriber_PhoneNumber(phoneNumber);

        mapBillToViewModel(subscriber.getBills(),billViewModels);
        subscriberViewModel.setBills(billViewModels);
        return subscriberViewModel;
    }

    @Override
    public List<BillViewModel> getHistoryBySubscriber(String phoneNumber, long bankId) {
        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId, phoneNumber);
        List<BillViewModel> billViewModels = new ArrayList<>();
        mapBillToViewModel(bills, billViewModels);
        return billViewModels;
    }

    @Override
    public List<SubscriberViewModel> listAllSubscribers(long bankId) {
        List<Subscriber> subscribers = this.subscriberRepository.getAllByBank_Id(bankId);
        List<SubscriberViewModel> subscriberViewModels = new ArrayList<>();
        for (Subscriber subscriber:subscribers) {
            SubscriberViewModel subscriberViewModel = new SubscriberViewModel();
            subscriberViewModel.setId(subscriber.getId());
            subscriberViewModel.setFullName(subscriber.getFirstName()+" "+subscriber.getLastName());
            subscriberViewModel.setEgn(subscriber.getEgn());
            subscriberViewModel.setPhoneNumber(subscriber.getPhoneNumber());
            subscriberViewModels.add(subscriberViewModel);
        }
        return subscriberViewModels;
    }

    @Override
    public Double averageAmount(List<String> timeInterval, String phoneNumber, long bankId) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            endDate = new Date(format.parse(timeInterval.get(1)).getTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        List<Bill> bills = billRepository.getByStartDateBetweenAndSubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(startDate, endDate, bankId, phoneNumber);
        double sum = 0;
        for (Bill bill : bills) {
            sum += bill.getAmount();
        }
        return sum / bills.size();
    }

    @Override
    public Double maxAmount(List<String> timeInterval, String phoneNumber, long bankId) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            endDate = new Date(format.parse(timeInterval.get(1)).getTime());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        List<Bill> bills = billRepository.getByStartDateBetweenAndSubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(startDate, endDate, bankId, phoneNumber);
        double max = 0;
        for (Bill bill : bills) {
            max = Math.max(max, bill.getAmount());
        }
        return max;
    }


    @Override
    public void payAllPaymentsBySubscriber(String phoneNumber, long bankId) {

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(bankId, phoneNumber);
        for (Bill bill : bills) {
            bill.setPaymentDate(new Date(System.currentTimeMillis()));
            this.billRepository.saveAndFlush(bill);
        }
    }

    @Override
    public void payBillById(int billId, long bankId, String phoneNumber) {
        Bill bill = billRepository.getByIdAndSubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(billId, bankId, phoneNumber);
        bill.setPaymentDate(new Date(System.currentTimeMillis()));
        this.billRepository.saveAndFlush(bill);
    }

    @Override
    public List<com.telerik.payment_system.entities.Service> getAllServices(String phoneNumber, long bankId) {

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId, phoneNumber);
        List<com.telerik.payment_system.entities.Service> services = new ArrayList<>();
        for (Bill bill : bills) {
            services.add(bill.getService());
        }
        return services;
    }

    @Override
    public HashMap<Subscriber, Double> findTop10(long bankId) {

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

    @Override
    public List<BillViewModel> getAllPaymentsBySubscriber(String phoneNumber, long bankId) {
        List<BillViewModel>billViewModels = new ArrayList<>();
        List<Bill> bills = this.billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumber(bankId,phoneNumber);
        mapBillToViewModel(bills,billViewModels);
        return billViewModels;
    }

    private void mapBillToViewModel(List<Bill> bills, List<BillViewModel> billViewModels) {
        for (Bill bill : bills) {
            BillViewModel billViewModel = new BillViewModel();
            billViewModel.setId(bill.getId());
            billViewModel.setSubscriber(bill.getSubscriber().getFirstName() + " " + bill.getSubscriber().getLastName());
            billViewModel.setPhoneNumber(bill.getSubscriber().getPhoneNumber());
            billViewModel.setService(bill.getService().getServiceName());
            billViewModel.setStartDate(bill.getStartDate());
            billViewModel.setEndDate(bill.getEndDate());
            billViewModel.setPaymentDate(bill.getPaymentDate());
            billViewModel.setAmount(bill.getAmount());
            billViewModel.setCurrency(bill.getCurrency().getCurrencyName());
            billViewModels.add(billViewModel);
        }
    }

}
