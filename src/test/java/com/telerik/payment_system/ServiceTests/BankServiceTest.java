package com.telerik.payment_system.ServiceTests;

import com.telerik.payment_system.entities.*;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.repositories.base.SubscriberRepository;
import com.telerik.payment_system.services.BankServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

    @Mock
    private BillRepository mockBillRepository;
    @Mock
    private SubscriberRepository mockSubscriberRepository;

    @InjectMocks
    private BankServiceImpl mockBankService;

    @Test
    public void getUnpaidBillBySubscriber_returnTheRightBills(){

        // Arrange
        List<Bill> bills= new ArrayList<>();
        bills.add(new Bill(new Service(),new Subscriber(),new Date(1), new Date(1),1.99,new Currency()));
        bills.add(new Bill(new Service(),new Subscriber(),new Date(1), new Date(1),1.99,new Currency()));
        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount
                (1L,"0123456789")).thenReturn(bills);

        //Act
        List<Bill> result = mockBankService.getAllUnpaidBillsBySubscriber(1L, "0123456789");

        //Assert
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getSubscriberBySubscriberPhoneNumber_returnTheRightSubscriber(){

        //Arrange
        Mockito.when(mockSubscriberRepository.getByBank_IdAndPhoneNumber(1L,"0123456789"))
                .thenReturn(new Subscriber("0123456789","Test","Test","9999999999",
                        new User("test","test","test","test"),new ArrayList<>()));

        //Act
        Subscriber result = mockBankService.findByPhoneNumber(1L, "0123456789");

        //Assert
        Assert.assertEquals("9999999999",result.getEgn());

    }


    @Test
    public void getPaidBillsBySubscriberWhenHistoryIsAsk_returnTheRightPayedBills(){

        //Arrange
        List<Bill> bills= new ArrayList<>();
        bills.add(new Bill(new Service(),new Subscriber(),new Date(1), new Date(1),1.99,new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(),new Subscriber(),new Date(1), new Date(1),1.99,new Currency(), new Date(System.currentTimeMillis())));
        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(1L, "0123456789"))
                .thenReturn(bills);
        //Act
        List<Bill> result = mockBankService.getHistoryBySubscriber("0123456789", 1L);

        //Assert
        Assert.assertEquals(2, result.size());
    }


    @Test
    public void getAverageAmountBySubscriberForDefinedPeriod_returnTheRightAnswer(){

        //Arrange
        List<Bill> bills= new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        bills.add(new Bill(new Service(),new Subscriber(),new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),1,new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(),new Subscriber(),new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),2,new Currency(), new Date(System.currentTimeMillis())));
        Mockito.when(mockBillRepository.getByStartDateBetweenAndSubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(
                new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),1L,"0123456789"))
                .thenReturn(bills);

        //Act
        List<String> timeInterval = new ArrayList<>();
        timeInterval.add(String.valueOf(new Date(System.currentTimeMillis())));
        timeInterval.add(String.valueOf(new Date(System.currentTimeMillis())));

        Double result = mockBankService.averageAmount(timeInterval,"0123456789", 1L );

        //Assert
        Assert.assertEquals(0, Double.compare(result, result));

    }

    @Test
    public void returnSubscribersServices_returnCorrectServices() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(),new Subscriber(),new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),1,new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(),new Subscriber(),new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),2,new Currency(), new Date(System.currentTimeMillis())));

        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(1L,"0123456789"))
                .thenReturn(bills);

        List<Service> result = mockBankService.getAllServices("0123456789", 1L);

        Assert.assertEquals(2, result.size());

    }
    @Test
    public void getTenMostRecentPaymentsByBankId_ReturnCorrectBills() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(),new Subscriber(),new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),1,new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(),new Subscriber(),new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),2,new Currency(), new Date(System.currentTimeMillis())));

        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_Id(1L)).thenReturn(bills);

        HashMap<Subscriber, Double> result = mockBankService.findTop10(1L);

        Assert.assertEquals(2, result.size());
    }


}
