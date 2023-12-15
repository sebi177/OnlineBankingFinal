package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.dto.AgreementFullDTO;
import com.example.onlinebankingfinal.model.Agreement;
import com.example.onlinebankingfinal.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agreement")
public class AgreementController {
    private final AgreementService agreementService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDTO> createAgreement(@RequestBody AgreementFullDTO agreement){
        AgreementDTO createdAgreement = agreementService.createAgreement(agreement);
        return new ResponseEntity<>(createdAgreement, HttpStatus.OK);
    }

    @GetMapping("/find/{agreementId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDTO> getAgreementById(@PathVariable UUID agreementId){
        AgreementDTO foundedAgreement = agreementService.getDtoById(agreementId);
        return new ResponseEntity<>(foundedAgreement, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AgreementDTO>> getAllAgreements(){
        List<AgreementDTO> agreementList = agreementService.getAllAgreements();
        return new ResponseEntity<>(agreementList, HttpStatus.OK);
    }

    @PostMapping("/update/{agreementId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AgreementDTO> updateAgreement(@PathVariable UUID agreementId,@RequestBody AgreementDTO agreementDTO){
        AgreementDTO updatedAgreement = agreementService.updateAgreement(agreementId, agreementDTO);
        return new ResponseEntity<>(updatedAgreement, HttpStatus.OK);
    }

    @PostMapping("/delete/{agreementId}")
    @ResponseStatus(HttpStatus.OK)
    public AgreementDTO deleteAgreement(@PathVariable UUID agreementId){
        return agreementService.deleteAgreement(agreementId);
    }
}
