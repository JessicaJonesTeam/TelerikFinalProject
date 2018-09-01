package com.telerik.payment_system.ServiceTests;


import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.viewModels.UserViewModel;
import com.telerik.payment_system.repositories.base.UserRepository;
import com.telerik.payment_system.services.AdminServiceImpl;
import com.telerik.payment_system.services.base.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private
    AdminServiceImpl mockAdminService;

    @Test
    public void CreateNewUser_ReturnsCorrectNewUser(){

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        Mockito.when(mockUserRepository.findAll()).thenReturn(users);

//        List<User> result = mockAdminService.getAllUsers();

//        Assert.assertEquals(2, result.size());


    }




}
