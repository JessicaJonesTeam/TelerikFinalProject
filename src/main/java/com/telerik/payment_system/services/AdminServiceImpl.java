package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Role;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.base.BillRepository;
import com.telerik.payment_system.repositories.base.RoleRepository;
import com.telerik.payment_system.repositories.base.UserRepository;
import com.telerik.payment_system.services.base.AdminService;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final BillRepository billRepository;


    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.billRepository = billRepository;
    }

    public UserDetails getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void createUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        System.out.println(roleRepository.findByAuthority(user.getRoles().get(0).getAuthority()));
        roles.add(roleRepository.findByAuthority(user.getRoles().get(0).getAuthority()));
        user.setRoles(roles);
        this.userRepository.saveAndFlush(user);

    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.getById(id);
    }

    @Override
    public void editUser(long id, User feed) {
        User user = this.userRepository.getById(id);
        user.setEmail(feed.getEmail());
        user.setUsername(feed.getUsername());
        user.setEIK(feed.getEIK());

        this.userRepository.saveAndFlush(user);

    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void createPayment(Bill billFeed) {
        this.billRepository.saveAndFlush(billFeed);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username was not found.");
        }

        return user;
    }
}