package com.rungroop.login.service.impl;

import java.util.Arrays;


import org.springframework.stereotype.Service;

import com.rungroop.login.dto.RegistrationDto;
import com.rungroop.login.models.Role;
import com.rungroop.login.models.UserEntity;
import com.rungroop.login.repository.RoleRepository;
import com.rungroop.login.repository.UserRepository;
import com.rungroop.login.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        System.out.println("Saving user: " + registrationDto.getUsername());
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        if (role == null) {
            role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        System.out.println("User saved successfully: " + user.getUsername());
    }

    @Override
    public UserEntity findByEmail(String email) {
       return userRepository.findByEmail(email); 
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
