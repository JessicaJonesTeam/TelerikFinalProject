package com.telerik.payment_system.repositories;

import com.telerik.payment_system.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
//•	A client should be able to see a list of the services the client has paid for
}
