package com.rungroop.login.service;

import com.rungroop.login.dto.RegistrationDto;
import com.rungroop.login.models.UserEntity;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
