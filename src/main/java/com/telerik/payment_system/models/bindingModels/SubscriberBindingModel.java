package com.telerik.payment_system.models.bindingModels;


import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.User;


import javax.validation.constraints.NotNull;
import java.util.List;

public class SubscriberBindingModel {

    @NotNull
    private String phoneNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String egn;

    @NotNull
    private User bank;

    @NotNull
    private List<Bill> bills;


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

}
