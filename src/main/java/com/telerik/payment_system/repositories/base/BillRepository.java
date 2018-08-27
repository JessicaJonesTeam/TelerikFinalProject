package com.telerik.payment_system.repositories.base;

import com.telerik.payment_system.entities.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {



    //They must have access to bill payment module where they can pay a particular bill (or selected list of bills) for their subscribers
    List<Bill> getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(Long bankId, String phoneNumber);

    // A client should be able to see a history of the payments for its subscribers sorted descending by the date of payment
    List<Bill> getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(Long bankId, String phoneNumber);

    List<Bill> getAllByStartDateAndEndDateAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(Date startDate, Date endDate,String phoneNumber);

    //List<Bill> findByStartDateBetweenAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(Date from, Date to, String phoneNumber);

    List<Bill> getAllBySubscriber_Bank_Id (Long bankId);
}