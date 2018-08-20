package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Currency;
import com.telerik.payment_system.repositories.CurrencyRepository;
import com.telerik.payment_system.services.base.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;


public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    @Override
    public Currency getById(int id) {
        return this.currencyRepository.getById(id);
    }

    @Override
    public void create(Currency currency) {
        this.currencyRepository.saveAndFlush(currency);
    }

    @Override
    public void update(int id, Currency currency) {
        Currency toUpdate = this.currencyRepository.getById(id);
        toUpdate.setCurrency(currency.getCurrency());
        toUpdate.setExchangeRate(currency.getExchangeRate());
    }

    @Override
    public void delete(int id) {
        this.currencyRepository.deleteById(id);
    }
}
