package com.telerik.payment_system.entities.viewModels;


public class UserBindingModel {

    private String username;

    private String password;

    private String email;

    private String EIK;


    public UserBindingModel() {
    }

    public UserBindingModel(String username, String password, String email, String firstName, String lastName, String eik) {
        this.username = username;
        this.password = password;
        this.email = email;

        EIK = eik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
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
}
