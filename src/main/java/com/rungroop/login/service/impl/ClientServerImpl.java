package com.rungroop.login.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.rungroop.login.dto.ClientDto;
import com.rungroop.login.models.Client;
import com.rungroop.login.repository.ClientRepository;
import com.rungroop.login.service.ClientService;

@Service

public class ClientServerImpl implements ClientService {
    private ClientRepository clientRepository;

    
    public ClientServerImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> findAllClients() {
       List<Client> clients = clientRepository.findAll();
       return clients.stream().map((client) -> mapToClientDto(client)).collect(Collectors.toList());
        
    }
    @Override
    public Client saveClient(ClientDto clientDto) {
        Client client = mapToClient(clientDto);
        return clientRepository.save(client);   

    }

    @Override
    public ClientDto findClientById(long clientId) {
        Client client = clientRepository.findById(clientId).get();
        return mapToClientDto(client);
    }
    
    @Override
    public void updateClient(ClientDto clientDto) {
            Client client = mapToClient(clientDto);
            clientRepository.save(client);
    }

    private Client mapToClient(ClientDto client) {
            Client clientDto = Client.builder()
            .id(client.getId())
            .nombre(client.getNombre())
            .apellido(client.getApellido())
            .pais(client.getPais())
            .correo(client.getCorreo())
            .edad(client.getEdad())
            .photoUrl(client.getPhotoUrl())
            .build();
            return clientDto;
        }

    private ClientDto mapToClientDto (Client client) {
        ClientDto clientdto = ClientDto.builder()
        .id(client.getId())
        .nombre(client.getNombre())
        .apellido(client.getApellido())
        .pais(client.getPais())
        .correo(client.getCorreo())
        .edad(client.getEdad())
        .photoUrl(client.getPhotoUrl())     
        .build();

        return clientdto;
    }

    @Override
    public void delete(Long clientId) {
       clientRepository.deleteById(clientId);
    }

    @Override
    public List<ClientDto> searchClients(String query) {
        List<Client> clients = clientRepository.searchClients(query);
        return clients.stream().map(client -> mapToClientDto(client)).collect(Collectors.toList());
    }

    

}
