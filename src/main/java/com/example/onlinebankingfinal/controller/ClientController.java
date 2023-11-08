package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AccountFullDTO;
import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.service.AccountService;
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
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ClientDTO> createClient(@RequestBody Client client) {
        ClientDTO createdCard = clientService.createClient(client);
        return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
    }

    @GetMapping("/find/{clientId}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable UUID clientId) {
        ClientDTO foundedClient = clientService.getDtoById(clientId);
        return new ResponseEntity<>(foundedClient, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        List<ClientDTO> clientList = clientService.getAllClients();
        return new ResponseEntity<>(clientList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable UUID clientId, @RequestBody ClientDTO clientDTO){
        ClientDTO updatedClient = clientService.updateClient(clientId, clientDTO);
        return new ResponseEntity<>(updatedClient, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{clientId}")
    public void deleteClient(@PathVariable UUID clientId){
        clientService.deleteClient(clientId);
    }

    @PostMapping("/{clientId}/createAccount")
    public ResponseEntity<AccountFullDTO> createAccountByClient(@PathVariable UUID clientId,@RequestBody Account account){
        AccountFullDTO thisAccount = accountService.createAccountByClient(clientId, account);
        return new ResponseEntity<>(thisAccount, HttpStatus.CREATED);
    }
}
