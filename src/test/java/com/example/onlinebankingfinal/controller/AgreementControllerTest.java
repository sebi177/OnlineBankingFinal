package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.service.AgreementService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
@Sql("/delete_tables.sql")
@Sql("/create_tables.sql")
@Sql("/insert_tables.sql")
@ActiveProfiles("test")
public class AgreementControllerTest {

    @Autowired
    AgreementService agreementService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createAgreement() throws Exception {

        AgreementDTO existent = new AgreementDTO();
        existent.setAgreementStatus("ACTIVE");
        existent.setInterestRate("3.45");
        existent.setAgreementSum("1000.0");

        String json = objectMapper.writeValueAsString(existent);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/agreement/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AgreementDTO current = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(existent, current);
    }

    @Test
    void getAgreementById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/agreement/find/95e4be3a-6551-4c0f-a632-662a049a40b8"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AgreementDTO actual = new AgreementDTO();
        actual.setAgreementStatus("ACTIVE");
        actual.setInterestRate("0.05");
        actual.setAgreementSum("50000.0");

        AgreementDTO current = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(current, actual);
    }

    @Test
    void getAllAgreements() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/agreement/findAll"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AgreementDTO firstAgreement = new AgreementDTO();
        firstAgreement.setAgreementStatus("ACTIVE");
        firstAgreement.setInterestRate("0.05");
        firstAgreement.setAgreementSum("50000.0");

        AgreementDTO secondAgreement = new AgreementDTO();
        secondAgreement.setAgreementStatus("ACTIVE");
        secondAgreement.setInterestRate("0.03");
        secondAgreement.setAgreementSum("30000.0");

        List<AgreementDTO> actual = new ArrayList<>();
        actual.add(firstAgreement);
        actual.add(secondAgreement);

        List<AgreementDTO> current = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(current, actual);
    }

    @Test
    void updateAgreement() throws Exception{
        AgreementDTO updatedAgreement = new AgreementDTO();
        updatedAgreement.setAgreementStatus("ACTIVE");
        updatedAgreement.setInterestRate("0.07");
        updatedAgreement.setAgreementSum("20000.0");

        String json = objectMapper.writeValueAsString(updatedAgreement);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/agreement/update/95e4be3a-6551-4c0f-a632-662a049a40b8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AgreementDTO updated = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(updatedAgreement, updated);
    }

    @Test
    void delete() throws Exception {
        AgreementDTO updatedAgreement = new AgreementDTO();
        updatedAgreement.setAgreementStatus("DELETED");
        updatedAgreement.setInterestRate("0.05");
        updatedAgreement.setAgreementSum("50000.0");

        String json = objectMapper.writeValueAsString(updatedAgreement);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/agreement/delete/95e4be3a-6551-4c0f-a632-662a049a40b8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        AgreementDTO deleted = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(updatedAgreement, deleted);
    }



}
