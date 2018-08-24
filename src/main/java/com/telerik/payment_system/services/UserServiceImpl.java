package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Bill;
import com.telerik.payment_system.entities.Role;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.repositories.BillRepository;
import com.telerik.payment_system.repositories.RoleRepository;
import com.telerik.payment_system.repositories.UserRepository;
import com.telerik.payment_system.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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

    public UserDetails getByUsername(String username) {
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

        if(user == null) {
            throw new UsernameNotFoundException("Username was not found.");
        }

        return user;
    }
}