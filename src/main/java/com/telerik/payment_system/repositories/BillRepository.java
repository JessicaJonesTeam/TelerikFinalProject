package com.telerik.payment_system.repositories;

import com.telerik.payment_system.entities.Bill;

import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {



    //They must have access to bill payment module where they can pay a particular bill (or selected list of bills) for their subscribers
    //List<Bill> getAllBySubscriber_PhoneNumberAndPaymentDateIsNull(String phoneNumber);
    List<Bill> getAllBySubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(String phoneNumber);

    List<Bill> getAllByStartDateAndEndDateAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(Date startDate, Date endDate,String phoneNumber);

    // A client should be able to see a history of the payments for its subscribers sorted descending by the date of payment
    List<Bill> getAllBySubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc (String phoneNumber);

    @Override
    List<Bill> findAll();
}