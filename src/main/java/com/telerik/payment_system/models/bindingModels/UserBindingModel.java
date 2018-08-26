package com.telerik.payment_system.models.bindingModels;

import com.telerik.payment_system.entities.Role;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserBindingModel {


    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String EIK;

    @NotNull
    private String email;

    @NotNull
    private List<Role> roles;


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEIK() {
        return this.EIK;
    }

    public void setEIK(String EIK) {
        this.EIK = EIK;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
