package com.telerik.payment_system.ServiceTests;

import com.telerik.payment_system.entities.*;
import com.telerik.payment_system.models.viewModels.BillViewModel;
import com.telerik.payment_system.models.viewModels.SubscriberViewModel;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.repositories.base.SubscriberRepository;
import com.telerik.payment_system.services.BankServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

    @Mock
    private BillRepository mockBillRepository;
    @Mock
    private SubscriberRepository mockSubscriberRepository;

    @InjectMocks
    private BankServiceImpl mockBankService;

    @Test
    public void getUnpaidBillBySubscriber_returnTheRightBills() {

        // Arrange
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency()));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency()));
        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount
                (1L, "0123456789")).thenReturn(bills);

        //Act
        List<BillViewModel> result = mockBankService.getAllUnpaidBillsBySubscriber(1L, "0123456789");

        //Assert
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getALLUnpaidBill_returnTheRightBills() {

        // Arrange
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency()));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency()));
        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndPaymentDateIsNullOrderByAmount(1L)).thenReturn(bills);

        //Act
        List<BillViewModel> result = mockBankService.getAllUnpaidBill(1L);

        //Assert
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getSubscriberBySubscriberPhoneNumber_returnTheRightSubscriber() {

        //Arrange
        Subscriber subscriber = new Subscriber
                ("0123456789", "test", "test", "9999999999", new User(), new ArrayList<>(), 1);
        Mockito.when(mockSubscriberRepository.getByBank_IdAndPhoneNumber(1L, "0123456789"))
                .thenReturn(subscriber);


        //Act
        SubscriberViewModel result = mockBankService.findByPhoneNumber(1L, "0123456789");

        //Assert
        Assert.assertEquals("9999999999", result.getEgn());

    }

    @Test
    public void getPaidBillsBySubscriberWhenHistoryIsAsk_returnTheRightPayedBills() {

        //Arrange
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency(), new Date(System.currentTimeMillis())));
        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc
                (1L, "0123456789")).thenReturn(bills);
        //Act
        List<BillViewModel> result = mockBankService.getHistoryBySubscriber("0123456789", 1L);

        //Assert
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getPaidBillsByBankIdWhenHistoryIsAsk_returnTheRightPayedBills() {

        //Arrange
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(1), new Date(1), 1.99, new Currency(), new Date(System.currentTimeMillis())));
        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndPaymentDateIsNotNullOrderByPaymentDateDesc
                (1L)).thenReturn(bills);
        //Act
        List<BillViewModel> result = mockBankService.getHistoryByBankID(1L);

        //Assert
        Assert.assertEquals(2, result.size());
    }


    @Test
    public void getAllSubscribersByBankId_returnRightSubscribers() {
        //Arrange
        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(new Subscriber("0123456789", "test", "test", "9999999999", new User(), new ArrayList<>(), 1));
        subscribers.add(new Subscriber("1123456789", "test1", "test1", "9999999999", new User(), new ArrayList<>(), 1));
        Mockito.when(mockSubscriberRepository.getAllByBank_Id(1L)).thenReturn(subscribers);

        //Act
        List<SubscriberViewModel> result = mockBankService.listAllSubscribers(1L);

        //Assert
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getAverageAmountBySubscriberForDefinedPeriod_returnTheRightAnswer() {

        //Arrange
        List<String> timeInterval = new ArrayList<>();
        timeInterval.add("2018-09-07");
        timeInterval.add("2018-09-09");

        Date startDate = Date.valueOf("2018-09-07");
        Date endDate = Date.valueOf("2018-09-09");

        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2, new Currency(), new Date(System.currentTimeMillis())));
        Mockito.when(mockBillRepository.findAllByPaymentDateIsNotNullAndSubscriber_BankIdAndSubscriberPhoneNumberAndPaymentDateIsBetween(
                1L, "0123456789", startDate, endDate))
                .thenReturn(bills);
        bills.forEach(x -> System.out.println(x.getPaymentDate()));

        //Act
        Double result = mockBankService.averageAmount(timeInterval, "0123456789", 1L);

        //Assert
        Assert.assertEquals(0, Double.compare(1.5, result));
    }

    @Test
    public void getMaxAmountBySubscriberForDefinedPeriod_returnTheRightAnswer() {

        //Arrange
        List<String> timeInterval = new ArrayList<>();
        timeInterval.add("2018-09-07");
        timeInterval.add("2018-09-09");

        Date startDate = Date.valueOf("2018-09-07");
        Date endDate = Date.valueOf("2018-09-09");

        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2, new Currency(), new Date(System.currentTimeMillis())));
        Mockito.when(mockBillRepository.findAllByPaymentDateIsNotNullAndSubscriber_BankIdAndSubscriberPhoneNumberAndPaymentDateIsBetween(
                1L, "0123456789", startDate, endDate))
                .thenReturn(bills);
        bills.forEach(x -> System.out.println(x.getPaymentDate()));

        //Act
        Double result = mockBankService.maxAmount(timeInterval, "0123456789", 1L);

        //Assert
        Assert.assertEquals(0, Double.compare(2, result));
    }

    @Test
    public void returnSubscribersServices_returnCorrectServices() {

        List<Bill> bills = new ArrayList<>();
        Service service1 = new Service("test1", new ArrayList<>());
        Service service2 = new Service("test2", new ArrayList<>());
        bills.add(new Bill(service1, new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(service2, new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2, new Currency(), new Date(System.currentTimeMillis())));

        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(1L, "0123456789"))
                .thenReturn(bills);

        Set<String> result = mockBankService.getAllServices("0123456789", 1L);

        Assert.assertEquals(2, result.size());

    }

    @Test
    public void payAllBillsBySubscriber() {
        Subscriber subscriber = new Subscriber("0123456789", "test", "test", "123", new User(), new ArrayList<>(), 2);
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, new Currency(), null));
        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(1L, "0123456789"))
                .thenReturn(bills);
        Mockito.when(mockSubscriberRepository.getByPhoneNumber("0123456789")).thenReturn(subscriber);
        mockBankService.payAllPaymentsBySubscriber("0123456789", 1L);

        Assert.assertNotNull(bills.get(0).getPaymentDate());

    }

    @Test
    public void payBillById() {

        Subscriber subscriber = new Subscriber("0123456789", "test", "test", "123", new User(), new ArrayList<>(), 2);
        Bill bill = new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, new Currency(), null);
        Mockito.when(mockBillRepository.getByIdAndSubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount
                (1, 1L, "0123456789")).thenReturn(bill);
        Mockito.when(mockSubscriberRepository.getByPhoneNumber("0123456789")).thenReturn(subscriber);
        mockBankService.payBillById(1, 1L, "0123456789");

        Assert.assertNotNull(bill.getPaymentDate());
    }

    @Test
    public void getAllBillsBySubscribers_ReturnRightBills() {

        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, new Currency(), new Date(System.currentTimeMillis())));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2, new Currency(), new Date(System.currentTimeMillis())));

        Mockito.when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumber(1L, "0123456789"))
                .thenReturn(bills);

        List<BillViewModel> result = mockBankService.getAllPaymentsBySubscriber("0123456789", 1L);

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void getTenMostRecentPaymentsByBankId_ReturnCorrectBills() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1, new Currency(), new Date(System.currentTimeMillis()-24*60*60*1000)));
        bills.add(new Bill(new Service(), new Subscriber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 2, new Currency(), new Date(System.currentTimeMillis())));

        Mockito.when(mockBillRepository.getFirst10BySubscriber_Bank_IdAndPaymentDateIsNotNullOrderByPaymentDateDesc(1L)).thenReturn(bills);

        List<BillViewModel> result = mockBankService.get10RecentPayments(1L);

        Assert.assertEquals(2, result.size());

    }

    @Test
    public void getFirst10SubscribersByTotalPaymentAmount_ReturnRightSubscribers() {

        List<Subscriber> subscribers = new ArrayList<>();
        subscribers.add(new Subscriber("01234567890","test","test","123",new User(),new ArrayList<>(),2));
        subscribers.add(new Subscriber("0123456781","test1","test1","123",new User(),new ArrayList<>(),1));

        Mockito.when(mockSubscriberRepository.getFirst10ByBankIdOrderByTotalAmountPayedDesc(1L)).thenReturn(subscribers);

        List<SubscriberViewModel> result = mockBankService.getFirst10SubscribersByTotalPaymentAmount(1L);

        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.get(0).getTotalPaid() > result.get(1).getTotalPaid());

    }


}
