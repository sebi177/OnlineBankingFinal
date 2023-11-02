package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.model.Agreement;
import com.example.onlinebankingfinal.model.enums.AgreementStatus;
import com.example.onlinebankingfinal.service.AgreementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AgreementControllerTest {

    private AgreementController agreementController;
    private AgreementService agreementService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        agreementService = Mockito.mock(AgreementService.class);
        agreementController = new AgreementController(agreementService);
        mockMvc = MockMvcBuilders.standaloneSetup(agreementController).build();
    }

    @Test
    public void testCreateAgreement() throws Exception {
        Agreement agreement = new Agreement();
        when(agreementService.createAgreement(any(Agreement.class))).thenReturn(agreement);

        mockMvc.perform(post("/agreement/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(agreement)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.interestRate").value(agreement.getInterestRate()))
                .andExpect(jsonPath("$.agreementSum").value(agreement.getAgreementSum()));
    }

    @Test
    public void testGetAgreementById() throws Exception {
        UUID agreementId = UUID.randomUUID();
        Agreement agreement = new Agreement();
        when(agreementService.getById(agreementId)).thenReturn(agreement);

        mockMvc.perform(get("/agreement/find/{agreementId}", agreementId.toString()))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.interestRate").value(agreement.getInterestRate()))
                .andExpect(jsonPath("$.agreementSum").value(agreement.getAgreementSum()));
    }

    @Test
    public void testGetAllAgreements() throws Exception {
        Agreement agreement = new Agreement();
        when(agreementService.getAllAgreements()).thenReturn(Collections.singletonList(agreement));

        mockMvc.perform(get("/agreement/findAll"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].interestRate").value(agreement.getInterestRate()))
                .andExpect(jsonPath("$[0].agreementSum").value(agreement.getAgreementSum()));
    }

    @Test
    public void testUpdateAgreement() throws Exception {
        UUID agreementId = UUID.randomUUID();
        AgreementDTO agreementDTO = new AgreementDTO();
        agreementDTO.setInterestRate(String.valueOf(5.00));
        agreementDTO.setAgreementSum(String.valueOf(1000.00));

        Agreement updatedAgreement = new Agreement();
        updatedAgreement.setInterestRate(5.0);
        updatedAgreement.setAgreementSum(BigDecimal.valueOf(1000.0));

        Mockito.when(agreementService.updateAgreement(agreementId, agreementDTO)).thenReturn(updatedAgreement);

        mockMvc.perform(MockMvcRequestBuilders.post("/agreement/update/{agreementId}", agreementId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(agreementDTO)))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDeleteAgreement() throws Exception {
        UUID agreementId = UUID.randomUUID();

        mockMvc.perform(delete("/agreement/delete/{agreementId}", agreementId.toString()))
                .andExpect(status().isOk());

        Mockito.verify(agreementService).deleteAgreement(agreementId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
