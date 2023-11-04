package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.dto.ClientFullDTO;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.Manager;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientDTO createClient(Client client);

    Client getById(UUID clientId);

    ClientDTO getDtoById(UUID clientId);

    List<ClientDTO> getAllClients();

    ClientDTO updateClient(UUID clientId, ClientDTO clientDTO);

    void deleteClient(UUID clientId);

//    Client generateRandomClient(UUID managerId);
}
