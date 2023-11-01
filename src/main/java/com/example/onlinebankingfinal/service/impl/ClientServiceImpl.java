package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.mapper.ClientMapper;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.repository.ClientRepository;
import com.example.onlinebankingfinal.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    @Override
    public Client getById(UUID clientId){
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
    }

    @Override
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(UUID clientId, ClientDTO clientDTO){
        Client existingClient = getById(clientId);
        clientMapper.updateClientFromDTO(clientDTO, existingClient);
        return clientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(UUID clientId){
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
        clientRepository.delete(existingClient);
    }
}
