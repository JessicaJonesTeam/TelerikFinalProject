package com.payment_system.repositories.base;

import com.payment_system.entities.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {


    //They must have access to bill payment module where they can pay a particular bill (or selected list of bills) for their subscribers
    List<Bill> getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(long bankId, String phoneNumber);

    List<Bill> getAllBySubscriber_Bank_IdAndPaymentDateIsNullOrderByAmount(long bankId);

    // A client should be able to see a history of the payments for its subscribers sorted descending by the date of payment
    List<Bill> getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNotNullOrderByPaymentDateDesc(long bankId, String phoneNumber);

    Bill getByIdAndSubscriber_Bank_IdAndSubscriber_PhoneNumberAndPaymentDateIsNullOrderByAmount(int billId, long bankId, String phoneNumber);

    List<Bill> getAllBySubscriber_Bank_Id(Long bankId);

    List<Bill> getAllBySubscriber_Bank_IdAndSubscriber_PhoneNumber(long bankId, String phoneNumber);

    List<Bill>getFirst10BySubscriber_Bank_IdAndPaymentDateIsNotNullOrderByPaymentDateDesc (long bankId);

    List<Bill>findAllByPaymentDateIsNotNullAndSubscriber_BankIdAndSubscriberPhoneNumberAndPaymentDateIsBetween( long bankId, String phoneNumber,Date startDate, Date endDate);

    List<Bill>getAllBySubscriber_Bank_IdAndPaymentDateIsNotNullOrderByPaymentDateDesc (long bankId);




}