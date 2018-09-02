package com.telerik.payment_system.services.base;

import com.telerik.payment_system.models.bindingModels.BillRecordBindingModel;
import com.telerik.payment_system.models.bindingModels.UserBindingModel;
import com.telerik.payment_system.models.bindingModels.UserEditBindingModel;
import com.telerik.payment_system.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {


    List<UserViewModel> getAllUsers();

    UserViewModel getUserByID(long id);

    void createUser(UserBindingModel userBindingModel);

    void editUser(UserEditBindingModel userEditBindingModel);

    void deleteUser(long id);

    void createPayment(BillRecordBindingModel billRecordBindingModel);

}
