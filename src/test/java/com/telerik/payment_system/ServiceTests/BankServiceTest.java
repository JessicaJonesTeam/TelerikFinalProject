package com.telerik.payment_system.ServiceTests;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.services.BankServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {

    @Mock
    private BillRepository mockBillRepository;

    @InjectMocks
    private BankServiceImpl mockBankService;

    @Test
    public void getUnpaidBillBySubscriber_returnTheRightBills(){
        Subscriber testSubs = new Subscriber();
        Bill testBill = new Bill();
        testBill.setSubscriber(testSubs);
        List<Bill> bills = new ArrayList<>();
        bills.add(testBill);

        when(mockBillRepository.getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(
                (long) 1, "0123456789")).thenReturn(bills);

        List<Bill> result = mockBankService.getAllNonPaymentBillsForSubscriber(1L, "0123456789");

        Assert.assertEquals(1, result.size());
    }

}
