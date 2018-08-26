package com.telerik.payment_system.repositories.base;


import com.telerik.payment_system.entities.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    //    The client must be able to see personal details of a subscriber
    Subscriber getByPhoneNumber(String phoneNumber);

}

