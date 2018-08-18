package com.telerik.payment_system.repositories;

import com.telerik.payment_system.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    //    The client must be able to see personal details of a subscriber
    Subscriber findByPhoneNumber(String phoneNumber);


}

