package com.payment_system.services.base;

import com.payment_system.models.bindingModels.BillRecordBindingModel;
import com.payment_system.models.bindingModels.ChangePassword;
import com.payment_system.models.bindingModels.UserBindingModel;
import com.payment_system.models.bindingModels.UserEditBindingModel;
import com.payment_system.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {


    List<UserViewModel> getAllUsers();

    UserViewModel getUserByID(long id);

    void createUser(UserBindingModel userBindingModel);

    void editUser(UserEditBindingModel userEditBindingModel);

    void deleteUser(String username);

    void createPayment(BillRecordBindingModel billRecordBindingModel);

    void changePassword(long userId, ChangePassword changePassword);

}
