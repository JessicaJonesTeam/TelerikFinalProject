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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;

    @Column
    private String service;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
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
        return this.id;
    }

    public void setServiceId(long serviceId) {
        this.id = serviceId;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<Bill> getBills() {
        return this.bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
