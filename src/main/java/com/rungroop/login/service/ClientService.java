package com.rungroop.login.service;

import java.util.List;

import com.rungroop.login.dto.ClientDto;
import com.rungroop.login.models.Client;


public interface ClientService {
    List<ClientDto> findAllClients();
    Client saveClient(ClientDto clientDto);
    ClientDto findClientById(long clientId);
    void updateClient(ClientDto client);
    void delete(Long clientId);
    List<ClientDto> searchClients (String query);

}
