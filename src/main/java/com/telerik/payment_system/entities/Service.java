package com.telerik.payment_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String serviceName;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bill> bills;

    public Service() {

    }

    public Service(String service, List<Bill> bills) {
        this.serviceName = service;
        this.bills = bills;
    }

    public Service(String service) {
        this.serviceName = service;
    }

    public long getServiceId() {
        return this.id;
    }

    public void setServiceId(long serviceId) {
        this.id = serviceId;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String service) {
        this.serviceName = service;
    }

    public List<Bill> getBills() {
        return this.bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
