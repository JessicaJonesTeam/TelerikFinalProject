package com.telerik.payment_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String currency;

    @Column
    private double exchangeRate;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
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
        return id;
    }

    public void setCurrencyId(int currencyId) {
        this.id = currencyId;
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
