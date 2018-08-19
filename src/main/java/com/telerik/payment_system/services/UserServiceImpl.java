package com.telerik.payment_system.services;

import com.telerik.payment_system.entities.Role;
import com.telerik.payment_system.entities.User;
import com.telerik.payment_system.models.UserBindingModel;
import com.telerik.payment_system.repositories.RoleRepository;
import com.telerik.payment_system.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    public User saveUser(final User feed) {
        User user=new User();
        Role userRole = this.roleRepository.findByName("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(feed.getPassword()));
        user.addRole(userRole);
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        return users;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    public User editUser(Long id, User feed) {
        User user = this.getUserById(id);
        user.setEmail(feed.getEmail());
        user.setUsername(feed.getUsername());
        user.setEIK(feed.getEIK());
        Set<Role> roles = feed.getRoles();


        return this.userRepository.save(user);
    }

    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }


}