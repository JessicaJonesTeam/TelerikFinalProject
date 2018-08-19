package com.telerik.payment_system.entities;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String service;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Bill> bills;

    public Service() {

    }

    public Service(String service, List<Bill> bills) {
        this.service = service;
        this.bills = bills;
    }

    public Service(String service) {
        this.service = service;
    }

    public long getServiceId() {
        return id;
    }

    public void setServiceId(long serviceId) {
        this.id = serviceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
