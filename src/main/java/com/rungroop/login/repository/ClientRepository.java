package com.rungroop.login.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rungroop.login.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCorreo(String url);
    @Query("SELECT c FROM Client c WHERE c.nombre LIKE CONCAT('%', :query, '%')")
    List<Client> searchClients(String query);
}