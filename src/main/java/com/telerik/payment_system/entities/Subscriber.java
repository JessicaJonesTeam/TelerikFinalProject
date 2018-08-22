package com.telerik.payment_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
    @JsonIgnore
    private List<Bill> bills;


    public Subscriber() {

    }

    public Subscriber(String phoneNumber, String firstName, String lastName, String egn, User bank, List<Bill> bills) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.egn = egn;
        this.bank = bank;
        this.bills = bills;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public User getBank() {
        return bank;
    }

    public void setBank(User bank) {
        this.bank = bank;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return firstName + " " +
                lastName + " " +
                phoneNumber + " ";
    }
}