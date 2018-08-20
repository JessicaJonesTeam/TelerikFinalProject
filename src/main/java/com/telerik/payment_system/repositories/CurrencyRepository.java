package com.telerik.payment_system.repositories;

import com.telerik.payment_system.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    Currency getById(int id);

}
