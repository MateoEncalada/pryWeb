package com.rungroop.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rungroop.login.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
