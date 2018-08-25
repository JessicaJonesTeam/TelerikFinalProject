package com.telerik.payment_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(nullable = false, unique = true)
    private String authority;


    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;


    public Role() {
        this.users = new ArrayList<>();
    }

    public Role(String authority) {
        this.authority = authority;
        this.users = new ArrayList<>();
    }


    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}