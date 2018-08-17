package com.telerik.payment_system.repositories;

import com.telerik.payment_system.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
//    o	The 10 most recent payments for the particular client for all the subscribers
    List<Bill> findFirst10ByOrderByPaymentDateDesc();

    // A client should be able to see a history of the payments for its subscribers sorted descending by the date of payment
    List<Bill> findAllByOrderByPaymentDateDesc();
}