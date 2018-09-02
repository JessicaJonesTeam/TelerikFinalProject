package com.telerik.payment_system.models.viewModels;


import com.telerik.payment_system.entities.User;



public class SubscriberViewModel {
    private String phoneNumber;
    private String fullName;
    private String egn;

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


}
