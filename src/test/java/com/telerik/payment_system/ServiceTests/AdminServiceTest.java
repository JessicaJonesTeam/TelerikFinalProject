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
import org.springframework.security.core.userdetails.UserDetails;

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
    public void getUserByUsername_ReturnRightUsers() {
        User user = new User("test1", "test1","123","123");

        Mockito.when(mockUserRepository.getByUsername(user.getUsername())).thenReturn(user);

        UserDetails userDetails = mockAdminService.loadUserByUsername("test1");

        Assert.assertEquals(userDetails.getUsername(), "test1");
    }

}
