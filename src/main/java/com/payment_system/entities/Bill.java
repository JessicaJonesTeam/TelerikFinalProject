package com.payment_system.entities;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private double amount;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column
    private Date paymentDate;


    public Bill() {

    }

    public Bill(Service service, Subscriber subscriber, Date startDate, Date endDate, double amount, Currency currency) {
        this.service = service;
        this.subscriber = subscriber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = null;
    }
    public Bill(Service service, Subscriber subscriber, Date startDate, Date endDate, double amount, Currency currency, Date paymentDate) {
        this.service = service;
        this.subscriber = subscriber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Subscriber getSubscriber() {
        return this.subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
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

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }


}