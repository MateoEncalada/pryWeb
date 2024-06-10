package com.rungroop.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rungroop.login.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername (String username);//si haty error cambiar username a userName
}
