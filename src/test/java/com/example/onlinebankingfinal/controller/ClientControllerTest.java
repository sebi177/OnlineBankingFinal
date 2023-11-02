package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClientControllerTest {

    private ClientController clientController;
    private ClientService clientService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        clientService = Mockito.mock(ClientService.class);
        clientController = new ClientController(clientService);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client(); // Creați un obiect Client de test
        when(clientService.createClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/client/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(client)))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.clientId").isNotEmpty()); // Asigurați-vă că există un clientId returnat
    }

    @Test
    public void testGetClientById() throws Exception {
        UUID clientId = UUID.randomUUID();
        Client client = new Client();
        when(clientService.getById(clientId)).thenReturn(client);

        mockMvc.perform(get("/client/find/{clientId}", clientId.toString()))
                .andExpect(status().isFound());
//                .andExpect(jsonPath("$.clientId").value(clientId.toString())); // Verificați că clientId corespunde
    }

    @Test
    public void testGetAllClients() throws Exception {
        Client client = new Client();
        when(clientService.getAllClients()).thenReturn(Collections.singletonList(client));

        mockMvc.perform(get("/client/findAll"))
                .andExpect(status().isFound());
//                .andExpect(jsonPath("$[0].clientId").isNotEmpty()); // Asigurați-vă că există un clientId în lista rezultată
    }

    @Test
    public void testUpdateClient() throws Exception {
        UUID clientId = UUID.randomUUID();
        ClientDTO clientDTO = new ClientDTO();
        // Configurați comportamentul serviciului pentru a returna un client actualizat
        Client updatedClient = new Client();
        when(clientService.updateClient(clientId, clientDTO)).thenReturn(updatedClient);

        mockMvc.perform(post("/client/update/{clientId}", clientId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isAccepted());
//                .andExpect(jsonPath("$.clientId").value(clientId.toString())); // Verificați că clientId corespunde
    }

    @Test
    public void testDeleteClient() throws Exception {
        UUID clientId = UUID.randomUUID();

        mockMvc.perform(delete("/client/delete/{clientId}", clientId.toString()))
                .andExpect(status().isOk());

        // Verificați că serviciul a fost apelat cu ID-ul corespunzător pentru ștergere
        Mockito.verify(clientService).deleteClient(clientId);
    }

    // O metodă utilitară pentru a serializa obiectul în JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
