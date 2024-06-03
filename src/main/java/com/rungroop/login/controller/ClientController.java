package com.rungroop.login.controller;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.rungroop.login.dto.ClientDto;
import com.rungroop.login.models.Client;
import com.rungroop.login.service.ClientService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/clients")
    public String listClients(Model model) {
        List<ClientDto> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        return "clients-lists";
    }
    @GetMapping("/clients/{clientId}")
    public String clientDetails(@PathVariable("clientId") long clientId, Model model) {
    ClientDto clientDto = clientService.findClientById(clientId);
    model.addAttribute("client", clientDto);
    return "clients-detail";
    }

    @GetMapping("/clients/news")
    public String createClientForm(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "clients-create";
    }
    @GetMapping("/clients/{clientId}/delete")
    public String deleteClient(@PathVariable ("clientId")Long clientId ) {
        clientService.delete(clientId);
        return "redirect:/clients";
    }
    @GetMapping("/clients/search")
    public String searchClub(@RequestParam (value = "query") String query, Model model) {
        List<ClientDto> clients = clientService.searchClients(query);
        model.addAttribute("clients", clients);
        return "clients-lists";
    }
    
    
    @PostMapping("/clients/news")
    public String saveClient(@Valid @ModelAttribute("client") ClientDto clientDto,BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("client", clientDto);
            return "clients-create";
        }
        clientDto.setEdad(0);
        clientService.saveClient(clientDto); // clientRepository.save(client);();
        
        return "redirect:/clients";
    }
    @GetMapping("/clients/{clientId}/edit")
    public String editClientForm(@PathVariable("clientId") long clientId, Model model) {
        ClientDto client = clientService.findClientById(clientId);
        model.addAttribute("client", client);
        return "clients-edit";
    }
    @PostMapping("/clients/{clientId}/edit")
    public String updateClient(@PathVariable ("clientId") Long clientId,
                                @Valid @ModelAttribute("client") ClientDto Client,
                                BindingResult result) {
    if (result.hasErrors()) {
        return "clients-edit";
    }
        Client.setId(clientId);
        Client.setEdad(0);
        clientService.updateClient(Client);
        return "redirect:/clients";             
    }    
}
