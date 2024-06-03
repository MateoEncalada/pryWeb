package com.rungroop.login.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rungroop.login.models.UserEntity;
import com.rungroop.login.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService { 
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUsername(username);
        if (user != null) {
            User authUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList())
            );
            return authUser;    
        }else {
            throw new UsernameNotFoundException("Invalid username or password");
        }    
        
    }

}
