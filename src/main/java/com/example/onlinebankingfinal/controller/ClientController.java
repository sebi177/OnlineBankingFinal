package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AccountFullDTO;
import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.dto.ClientFullDTO;
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
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO createClient(@RequestBody ClientFullDTO client) {
        return clientService.createClient(client);
    }

    @GetMapping("/find/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable UUID clientId) {
        return clientService.getDtoById(clientId);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping("/update/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable UUID clientId, @RequestBody ClientDTO clientDTO){
        return clientService.updateClient(clientId, clientDTO);
    }

    @PostMapping("/delete/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO deleteClient(@PathVariable UUID clientId){
        return clientService.deleteClient(clientId);
    }

    @PostMapping("/{clientId}/createAccount")
    @ResponseStatus(HttpStatus.OK)
    public AccountFullDTO createAccountByClient(@PathVariable UUID clientId,@RequestBody Account account){
        return accountService.createAccountByClient(clientId, account);
    }
}
