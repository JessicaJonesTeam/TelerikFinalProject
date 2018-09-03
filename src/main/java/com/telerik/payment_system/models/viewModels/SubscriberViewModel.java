package com.telerik.payment_system.models.viewModels;


import com.telerik.payment_system.entities.User;

import java.util.ArrayList;
import java.util.List;


public class SubscriberViewModel {
    private long id;
    private String phoneNumber;
    private String fullName;
    private String egn;
    private List<BillViewModel> bills;


    public SubscriberViewModel() {
        this.bills=new ArrayList<>();
    }

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

    public List<BillViewModel> getBills() {
        return bills;
    }

    public void setBills(List<BillViewModel> bills) {
        this.bills = bills;
    }
}
