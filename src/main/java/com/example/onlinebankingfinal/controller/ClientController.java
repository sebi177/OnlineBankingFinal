package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdCard = clientService.createClient(client);
        return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
    }

    @GetMapping("/find/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID clientId) {
        Client foundedClient = clientService.getById(clientId);
        return new ResponseEntity<>(foundedClient, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clientList = clientService.getAllClients();
        return new ResponseEntity<>(clientList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID clientId, @RequestBody ClientDTO clientDTO){
        Client updatedClient = clientService.updateClient(clientId, clientDTO);
        return new ResponseEntity<>(updatedClient, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{clientId}")
    public void deleteClient(@PathVariable UUID clientId){
        clientService.deleteClient(clientId);
    }
}
