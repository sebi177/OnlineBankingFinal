package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.dto.ClientFullDTO;
import com.example.onlinebankingfinal.generator.ClientGenerator;
import com.example.onlinebankingfinal.mapper.ClientMapper;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.enums.ClientStatus;
import com.example.onlinebankingfinal.repository.ClientRepository;
import com.example.onlinebankingfinal.repository.ManagerRepository;
import com.example.onlinebankingfinal.service.ClientService;
import com.github.javafaker.Faker;
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
    private final ManagerRepository managerRepository;
//    private final Faker faker;


    @Override
    public ClientDTO createClient(Client client) {
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Override
    public Client getById(UUID clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
    }

    @Override
    public ClientDTO getDtoById(UUID clientId) {
        return clientMapper.toDto(getById(clientId));
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientMapper.listToDto(clientRepository.findAll());
    }

    @Override
    public ClientDTO updateClient(UUID clientId, ClientDTO clientDTO) {
        Client existingClient = getById(clientId);
        clientMapper.updateClientFromDTO(clientDTO, existingClient);
        return clientMapper.toDto(clientRepository.save(existingClient));
    }

    @Override
    public void deleteClient(UUID clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found!"));
        clientRepository.delete(existingClient);
    }

//    @Override
//    public ClientFullDTO generateRandomClient(UUID managerId) {
//        Client newClient = new Client();
//        newClient.setClientStatus(ClientStatus.ACTIVE);
//        newClient.setManager(managerRepository.getReferenceById(managerId));
//        newClient.setFirstName(faker.name().lastName());
//
//    }
}
