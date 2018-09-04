package com.telerik.payment_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "subscribers")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String phoneNumber;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String egn;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private User bank;

    @OneToMany(mappedBy = "subscriber")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Bill> bills;


    @Column
    private double totalAmountPayed;


    public Subscriber() {
        bills = new ArrayList<>();
    }

    public Subscriber(String phoneNumber, String firstName, String lastName, String egn, User bank, List<Bill> bills, double totalAmountPayed) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.egn = egn;
        this.bank = bank;
        this.bills = bills;
        this.totalAmountPayed = totalAmountPayed;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return this.egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public User getBank() {
        return this.bank;
    }

    public void setBank(User bank) {
        this.bank = bank;
    }

    public List<Bill> getBills() {
        return this.bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalAmountPayed() {
        return totalAmountPayed;
    }

    public void setTotalAmountPayed(double totalAmountPayed) {
        this.totalAmountPayed = totalAmountPayed;
    }


    @Override
    public String toString() {
        return firstName + " " +
                lastName + " " +
                phoneNumber + " ";
    }
}