package com.telerik.payment_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    @Column
    private String currencyName;

    @Column
    private double exchangeRate;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
    private List<Bill> bills;

    public Currency() {
        this.bills = new ArrayList<>();
    }

    public Currency(String currency, double exchangeRate, List<Bill> bills) {
        this.currencyName = currency;
        this.exchangeRate = exchangeRate;
        this.bills = bills;
    }

    public int getCurrencyId() {
        return this.id;
    }

    public void setCurrencyId(int currencyId) {
        this.id = currencyId;
    }

    public String getCurrencyName() {
        return this.currencyName;
    }

    public void setCurrency(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getExchangeRate() {
        return this.exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public List<Bill> getBills() {
        return this.bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public void addBill(Bill bill){
        this.bills.add(bill);
    }
}
