package com.payment_system.models.viewModels;


public class UserViewModel {

    private Long id;

    private String username;

    private String EIK;

    private String email;


    private String role;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEIK() {
        return EIK;
    }

    public void setEIK(String EIK) {
        this.EIK = EIK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
