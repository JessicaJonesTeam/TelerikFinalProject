package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.models.viewModels.BillViewModel;
import com.telerik.payment_system.models.viewModels.SubscriberViewModel;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.repositories.base.SubscriberRepository;
import com.telerik.payment_system.services.base.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final BillRepository billRepository;

    private final SubscriberRepository subscriberRepository;


    @Autowired
    public BankServiceImpl(BillRepository billRepository,
                           SubscriberRepository subscriberRepository) {
        this.billRepository = billRepository;
        this.subscriberRepository = subscriberRepository;
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
        Subscriber subscriber = subscriberRepository.getByBank_IdAndPhoneNumber(bankId, phoneNumber);
        SubscriberViewModel subscriberViewModel = new SubscriberViewModel();
        subscriberViewModel.setId(subscriber.getId());
        subscriberViewModel.setFullName(subscriber.getFirstName() + " " + subscriber.getLastName());
        subscriberViewModel.setEgn(subscriber.getEgn());
        subscriberViewModel.setPhoneNumber(subscriber.getPhoneNumber());

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
    public List<BillViewModel> getHistoryByBankID( long bankId) {
        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId);
        List<BillViewModel> billViewModels = new ArrayList<>();
        mapBillToViewModel(bills, billViewModels);
        return billViewModels;
    }

    @Override
    public List<SubscriberViewModel> listAllSubscribers(long bankId) {
        List<Subscriber> subscribers = this.subscriberRepository.getAllByBank_Id(bankId);
        List<SubscriberViewModel> subscriberViewModels = new ArrayList<>();
        mapSubscribersToViewModels(subscribers, subscriberViewModels);
        return subscriberViewModels;
    }

    @Override
    public Double averageAmount(List<String> timeInterval, String phoneNumber, long bankId) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            System.out.println(startDate);
            endDate = new Date(format.parse(timeInterval.get(1)).getTime());
            System.out.println(endDate);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        List<Bill> bills = billRepository.findAllByPaymentDateIsNotNullAndSubscriber_BankIdAndSubscriberPhoneNumberAndPaymentDateIsBetween(bankId,phoneNumber,startDate, endDate);
        System.out.println(bills.size());
        double sum = 0;
        for (Bill bill : bills) {

            sum += bill.getAmount();
        }
        if (bills.size()==0){
            return 0.0;
        }
        return sum / bills.size();
    }

    @Override
    public Double maxAmount(List<String> timeInterval, String phoneNumber, long bankId) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            System.out.println(startDate);
            endDate = new Date(format.parse(timeInterval.get(1)).getTime());
            System.out.println(endDate);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        List<Bill> bills = billRepository.findAllByPaymentDateIsNotNullAndSubscriber_BankIdAndSubscriberPhoneNumberAndPaymentDateIsBetween(  bankId,  phoneNumber, startDate,  endDate);
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
            Subscriber subscriber= subscriberRepository.getByPhoneNumber(phoneNumber);
            subscriber.setTotalAmountPayed(subscriber.getTotalAmountPayed()+bill.getAmount());
            this.subscriberRepository.saveAndFlush(subscriber);
            this.billRepository.saveAndFlush(bill);
        }
    }

    @Override
    public void payBillById(int billId, long bankId, String phoneNumber) {
        Bill bill = billRepository.getByIdAndSubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(billId, bankId, phoneNumber);
        bill.setPaymentDate(new Date(System.currentTimeMillis()));
        Subscriber subscriber= subscriberRepository.getByPhoneNumber(phoneNumber);
        subscriber.setTotalAmountPayed(subscriber.getTotalAmountPayed()+bill.getAmount());
        this.subscriberRepository.saveAndFlush(subscriber);
        this.billRepository.saveAndFlush(bill);
    }

    @Override
    public Set<String> getAllServices(String phoneNumber, long bankId) {

        List<Bill> bills = billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId, phoneNumber);

        Set<String> services = new HashSet<>();
        for (Bill bill : bills) {
           services.add(bill.getService().getServiceName());
        }
        return services;
    }

    @Override
    public List<BillViewModel> getAllPaymentsBySubscriber(String phoneNumber, long bankId) {
        List<BillViewModel> billViewModels = new ArrayList<>();
        List<Bill> bills = this.billRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumber(bankId, phoneNumber);
        mapBillToViewModel(bills, billViewModels);
        return billViewModels;
    }

    @Override
    public List<BillViewModel> get10RecentPayments(long bankId) {
        List<BillViewModel> billViewModels= new ArrayList<>();
        List<Bill> bills = this.billRepository.getFirst10BySubscriber_Bank_IdAndPaymentDateIsNotNullOrderByPaymentDateDesc(bankId);
        mapBillToViewModel(bills,billViewModels);
        return billViewModels;
    }

    @Override
    public List<SubscriberViewModel> getFirst10SubscribersByTotalPaymentAmount(long bankId) {
        List<Subscriber> subscribers = this.subscriberRepository.getFirst10ByBankIdOrderByTotalAmountPayedDesc(bankId);
        List<SubscriberViewModel> subscriberViewModels = new ArrayList<>();
        mapSubscribersToViewModels(subscribers, subscriberViewModels);
        return subscriberViewModels;
    }

    private void mapSubscribersToViewModels(List<Subscriber> subscribers, List<SubscriberViewModel> subscriberViewModels) {
        for (Subscriber subscriber : subscribers) {
            SubscriberViewModel subscriberViewModel = new SubscriberViewModel();
            Set<String>services = getAllServices(subscriber.getPhoneNumber(),subscriber.getBank().getId());
            subscriberViewModel.setServices(services);
            subscriberViewModel.setId(subscriber.getId());
            subscriberViewModel.setFullName(subscriber.getFirstName() + " " + subscriber.getLastName());
            subscriberViewModel.setEgn(subscriber.getEgn());
            subscriberViewModel.setPhoneNumber(subscriber.getPhoneNumber());
            subscriberViewModel.setTotalPaid(subscriber.getTotalAmountPayed());
            subscriberViewModels.add(subscriberViewModel);
        }
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
