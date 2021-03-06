package com.payment_system.services;

import com.payment_system.entities.Bill;
import com.payment_system.entities.Role;
import com.payment_system.entities.User;
import com.payment_system.models.bindingModels.BillRecordBindingModel;
import com.payment_system.models.bindingModels.ChangePassword;
import com.payment_system.models.bindingModels.UserBindingModel;
import com.payment_system.models.bindingModels.UserEditBindingModel;
import com.payment_system.models.viewModels.UserViewModel;
import com.payment_system.repositories.base.*;
import com.payment_system.services.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final SubscriberRepository subscriberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final BillRepository billRepository;

    private final ServiceRepository serviceRepository;

    private final CurrencyRepository currencyRepository;


    @Autowired
    public AdminServiceImpl(UserRepository userRepository1, RoleRepository roleRepository1, SubscriberRepository subscriberRepository1, BCryptPasswordEncoder bCryptPasswordEncoder1, BillRepository billRepository1, ServiceRepository serviceRepository, CurrencyRepository currencyRepository) {
        this.userRepository = userRepository1;

        this.roleRepository = roleRepository1;
        this.subscriberRepository = subscriberRepository1;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder1;
        this.billRepository = billRepository1;
        this.serviceRepository = serviceRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void createUser(UserBindingModel userBindingModel) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(userBindingModel.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (userBindingModel.getRoles().get(0).getAuthority().equals(this.roleRepository.getOne(1L).getAuthority())) {
            roles.add(roleRepository.findByAuthority("ROLE_CHANGEPASSWORD"));
        } else {
            roles.add(roleRepository.findByAuthority(userBindingModel.getRoles().get(0).getAuthority()));
        }
        user.setRoles(roles);
        user.setEnabled(true);
        user.setRoles(roles);
        user.setUsername(userBindingModel.getUsername());
        user.setEIK(userBindingModel.getEIK());
        user.setEmail(userBindingModel.getEmail());
        this.userRepository.saveAndFlush(user);

}

    @Override
    public List<UserViewModel> getAllUsers() {
        List<User> users = this.userRepository.getAllByEnabledIsTrue();
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (User user : users) {
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setId(user.getId());
            userViewModel.setUsername(user.getUsername());
            userViewModel.setEmail(user.getEmail());
            userViewModel.setEIK(user.getEIK());
            userViewModel.setRole(user.getRoles().get(0).getAuthority());
            userViewModels.add(userViewModel);
        }
        return userViewModels;
    }

    @Override
    public UserViewModel getUserByID(long id) {
        User user = this.userRepository.getById(id);
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(user.getId());
        userViewModel.setUsername(user.getUsername());
        userViewModel.setEmail(user.getEmail());
        userViewModel.setEIK(user.getEIK());
        userViewModel.setRole(user.getRoles().get(0).getAuthority());
        return userViewModel;

    }

    @Override
    public void editUser(UserEditBindingModel userEditBindingModel) {

        long id = userEditBindingModel.getId();
        User user = this.userRepository.getOne(id);
        user.setPassword(bCryptPasswordEncoder.encode(userEditBindingModel.getPassword()));
        user.setUsername(userEditBindingModel.getUsername());
        user.setEIK(userEditBindingModel.getEIK());
        user.setEmail(userEditBindingModel.getEmail());

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void deleteUser(String username) {
        User user = (User) this.loadUserByUsername(username);
        user.setEnabled(false);
        this.userRepository.saveAndFlush(user);
    }


    @Override
    public void createPayment(BillRecordBindingModel billFeed) {
        Bill bill = new Bill();
        // TODO: CHANGED

        String phone = billFeed.getSubscriberPhone();
        bill.setSubscriber(subscriberRepository.getByPhoneNumber(phone));
        String serviceName = billFeed.getService().getServiceName();
        bill.setService(serviceRepository.getByServiceName(serviceName));
        bill.setAmount(billFeed.getAmount());
        bill.setStartDate(billFeed.getStartDate());
        bill.setEndDate(billFeed.getEndDate());
        String currencyName = billFeed.getCurrencyName();
        bill.setCurrency(currencyRepository.getByCurrencyName(currencyName));
        this.billRepository.saveAndFlush(bill);
    }

    @Override
    public void changePassword(long userId, ChangePassword changePassword) {
        User user = userRepository.getById(userId);
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleRepository.getOne(1L));
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(changePassword.getPassword()));
        this.userRepository.saveAndFlush(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.userRepository.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username was not found.");
        }

        return user;
    }
}