package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Role;
import com.telerik.payment_system.entities.Subscriber;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.bindingModels.BillRecordBindingModel;
import com.telerik.payment_system.models.bindingModels.UserBindingModel;
import com.telerik.payment_system.models.bindingModels.UserEditBindingModel;
import com.telerik.payment_system.models.viewModels.UserViewModel;
import com.telerik.payment_system.repositories.base.*;
import com.telerik.payment_system.services.base.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
//        System.out.println(roleRepository.findByAuthority(userBindingModel.getRoles().get(0).getAuthority()));
        roles.add(roleRepository.findByAuthority(userBindingModel.getRoles().get(0).getAuthority()));
        user.setRoles(roles);
        user.setUsername(userBindingModel.getUsername());
        user.setEIK(userBindingModel.getEIK());
        user.setEmail(userBindingModel.getEmail());
        this.userRepository.saveAndFlush(user);

    }

    @Override
    public List<UserViewModel> getAllUsers() {
        List<User> users = this.userRepository.findAll();
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
    public void deleteUser(long id) {
        User user = this.userRepository.getOne(id);
        this.userRepository.delete(user);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.userRepository.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username was not found.");
        }

        return user;
    }
}