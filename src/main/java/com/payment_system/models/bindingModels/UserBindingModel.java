package com.payment_system.models.bindingModels;

import com.payment_system.constants.Constants;
import com.payment_system.entities.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserBindingModel {


    @Size(min =3,max = 20,message = Constants.USERNAME_LENGTH)
    @NotNull
    private String username;

    @Size(min = 4,max = 30,message = Constants.PASSWORD_LENGTH)
    @NotNull
    private String password;

    @Size(min =13,max = 15,message = Constants.EIK_LENGTH)
    @NotNull
    private String EIK;

    @Pattern(regexp = "^[A-Za-z0-9][A-Za-z0-9.-_]*[A-Za-z0-9]@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\\w+$", message = Constants.INVALID_EMAIL)
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
