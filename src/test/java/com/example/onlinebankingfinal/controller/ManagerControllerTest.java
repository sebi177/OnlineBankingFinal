package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.model.Manager;
import com.example.onlinebankingfinal.service.ManagerService;
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

public class ManagerControllerTest {

    private ManagerController managerController;
    private ManagerService managerService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        managerService = Mockito.mock(ManagerService.class);
        managerController = new ManagerController(managerService);
        mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
    }

    @Test
    public void testCreateManager() throws Exception {
        Manager manager = new Manager(); // Creați un obiect Manager de test
        when(managerService.createManager(any(Manager.class))).thenReturn(manager);

        mockMvc.perform(post("/manager/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(manager)))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.managerId").isNotEmpty()); // Asigurați-vă că există un managerId returnat
    }

    @Test
    public void testGetManagerById() throws Exception {
        UUID managerId = UUID.randomUUID();
        Manager manager = new Manager();
        when(managerService.getById(managerId)).thenReturn(manager);

        mockMvc.perform(get("/manager/find/{managerId}", managerId.toString()))
                .andExpect(status().isFound());
//                .andExpect(jsonPath("$.managerId").value(managerId.toString())); // Verificați că managerId corespunde
    }

    @Test
    public void testGetAllManagers() throws Exception {
        Manager manager = new Manager();
        when(managerService.getAllManagers()).thenReturn(Collections.singletonList(manager));

        mockMvc.perform(get("/manager/findAll"))
                .andExpect(status().isFound());
//                .andExpect(jsonPath("$[0].managerId").isNotEmpty()); // Asigurați-vă că există un managerId în lista rezultată
    }

    @Test
    public void testUpdateManager() throws Exception {
        UUID managerId = UUID.randomUUID();
        ManagerDTO managerDTO = new ManagerDTO();
        // Configurați comportamentul serviciului pentru a returna un manager actualizat
        Manager updatedManager = new Manager();
        when(managerService.updateManager(managerId, managerDTO)).thenReturn(updatedManager);

        mockMvc.perform(post("/manager/update/{managerId}", managerId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(managerDTO)))
                .andExpect(status().isAccepted());
//                .andExpect(jsonPath("$.managerId").value(managerId.toString())); // Verificați că managerId corespunde
    }

    @Test
    public void testDeleteManager() throws Exception {
        UUID managerId = UUID.randomUUID();

        mockMvc.perform(delete("/manager/delete/{managerId}", managerId.toString()))
                .andExpect(status().isOk());

        // Verificați că serviciul a fost apelat cu ID-ul corespunzător pentru ștergere
        Mockito.verify(managerService).deleteManager(managerId);
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
