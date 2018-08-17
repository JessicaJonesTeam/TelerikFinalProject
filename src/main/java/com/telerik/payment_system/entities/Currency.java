package com.telerik.payment_system.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CurrencyID")
    private int currencyId;

    @Column(name = "Currency")
    private String currency;

    @Column(name = "ExchangeRate")
    private double exchangeRate;

    @OneToMany(mappedBy = "currency")
    private List<Bill> bills;

    public Currency() {
        this.bills = new ArrayList<>();
    }

    public Currency(String currency, double exchangeRate, List<Bill> bills) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.bills = bills;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
