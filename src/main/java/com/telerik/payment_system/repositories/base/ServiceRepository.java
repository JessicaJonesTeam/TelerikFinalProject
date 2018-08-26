package com.telerik.payment_system.repositories.base;

import com.telerik.payment_system.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    Service getByServiceName(String name);
//•	A client should be able to see a list of the services the client has paid for


}
