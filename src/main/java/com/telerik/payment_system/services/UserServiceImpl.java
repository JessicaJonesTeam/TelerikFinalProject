package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Role;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.BillRepository;
import com.telerik.payment_system.repositories.RoleRepository;
import com.telerik.payment_system.repositories.UserRepository;
import com.telerik.payment_system.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        this.billRepository = billRepository;
    }

    public User getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void createUser(User feed) {
        User user = new User(
                feed.getUsername(),
                feed.getEmail(),
                feed.getEIK(),
                bCryptPasswordEncoder.encode(feed.getPassword())
        );

        Role userRole = this.roleRepository.findByAuthority("ROLE_USER");

        user.getRoles().add(userRole);

        this.userRepository.saveAndFlush(user);
        this.roleRepository.saveAndFlush(userRole);
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
    public void editUser(String id, User feed) {
        long userID = Long.parseLong(id);
        User user = this.userRepository.getById(userID);
        user.setEmail(feed.getEmail());
        user.setUsername(feed.getUsername());
        user.setEIK(feed.getEIK());

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(String id) {
        long userID = Long.parseLong(id);
        this.userRepository.deleteById(userID);
    }

    @Override
    public void createPayment(Bill billFeed) {
        this.billRepository.saveAndFlush(billFeed);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("Username was not found.");
        }

        return user;
    }
}