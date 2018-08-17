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

    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    public User getByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    public User saveUser(final UserBindingModel userRegistrationBindingModel) {
        User user = modelMapper.map(userRegistrationBindingModel, User.class);
        Role userRole = this.roleRepository.findByName("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationBindingModel.getPassword()));
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

    public User editUser(Long id, UserBindingModel userEditBindingModel) {
        User user = this.getUserById(id);
        user.setEmail(userEditBindingModel.getEmail());
        user.setUsername(userEditBindingModel.getUsername());
        user.setEIK(userEditBindingModel.getEIK());
        Set<Role> roles = new HashSet<>();

//        TODO: EDIT ROLES

        return this.userRepository.save(user);
    }

    public void deleteUser(String username) {
        this.userRepository.deleteByUsername(username);
    }


}