package com.telerik.payment_system.entities;



import javax.persistence.*;

@Entity
@Table(name = "subscribers")
public class Subscriber {
    @Id
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "EGN")
    private String EGN;

    @ManyToOne
    @Column(name = "AddressID")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "BankID")
    private User bank;

    public Subscriber() {
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

    public String getEGN() {
        return EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getBank() {
        return bank;
    }

    public void setBank(User bank) {
        this.bank = bank;
    }


}
