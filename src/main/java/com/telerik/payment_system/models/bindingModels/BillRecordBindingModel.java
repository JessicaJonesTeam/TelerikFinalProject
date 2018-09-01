package com.telerik.payment_system.models.bindingModels;

import com.telerik.payment_system.entities.Currency;
import com.telerik.payment_system.entities.Service;
import com.telerik.payment_system.entities.Subscriber;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class BillRecordBindingModel {

    @NotNull
    private Service service;

    @NotNull
    private String subscriberPhone;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private double amount;

    @NotNull
    private String currencyName;

    @NotNull
    private Date paymentDate;

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSubscriberPhone() {
        return subscriberPhone;
    }

    public void setSubscriberPhone(String subscriberPhone) {
        this.subscriberPhone = subscriberPhone;
    }
}
