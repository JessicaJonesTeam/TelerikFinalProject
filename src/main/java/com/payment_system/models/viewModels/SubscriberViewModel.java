package com.payment_system.models.viewModels;


import java.util.Set;


public class SubscriberViewModel {
    private long id;
    private String phoneNumber;
    private String fullName;
    private String egn;
    private double totalPaid;
    private Set<String> services;



    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEgn() {
        return this.egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }
}
