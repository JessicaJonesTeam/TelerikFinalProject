package com.telerik.payment_system.services.base;

import com.telerik.payment_system.entities.Currency;


public interface CurrencyService {

    Currency getById(int id);

    void create(Currency currency);

    void update(int id, Currency currency);

    void delete(int id);
}
