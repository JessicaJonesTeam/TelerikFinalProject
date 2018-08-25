package com.telerik.payment_system.entities;

import javax.persistence.*;


@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String zipCode;

    @Column
    private String street;

    public Address() {

    }

    public Address(String country, String city, String zipCode, String street) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
    }

    public int getAddressId() {
        return this.id;
    }

    public void setAddressId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
