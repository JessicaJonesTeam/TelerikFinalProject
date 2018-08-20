package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Role;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.BillRepository;
import com.telerik.payment_system.repositories.RoleRepository;
import com.telerik.payment_system.repositories.UserRepository;
import com.telerik.payment_system.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final BillRepository billRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.billRepository=billRepository;
    }

    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void createUser(User feed) {
        User user = new User();
        Role userRole = this.roleRepository.findByName("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(feed.getPassword()));
        user.addRole(userRole);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        return users;
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.getById(id);
    }

    @Override
    public void editUser(Long id, User feed) {
        User user = this.userRepository.getById(id);
        user.setEmail(feed.getEmail());
        user.setUsername(feed.getUsername());
        user.setEIK(feed.getEIK());
        Set<Role> roles = feed.getRoles();


        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void createPayment(Bill billFeed) {
        this.billRepository.saveAndFlush(billFeed);
    }


}