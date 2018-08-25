package com.telerik.payment_system.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(nullable = false, unique = true)
    private String authority;

    @Column
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<User> users;


    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;

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