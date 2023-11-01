package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.AgreementDTO;
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
    public ResponseEntity<Agreement> createAgreement(@RequestBody Agreement agreement){
        Agreement createdAgreement = agreementService.createAgreement(agreement);
        return new ResponseEntity<>(createdAgreement, HttpStatus.CREATED);
    }

    @GetMapping("/find/{agreementId}")
    public ResponseEntity<Agreement> getAgreementById(@PathVariable UUID agreementId){
        Agreement foundedAgreement = agreementService.getById(agreementId);
        return new ResponseEntity<>(foundedAgreement, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Agreement>> getAllAgreements(){
        List<Agreement> agreementList = agreementService.getAllAgreements();
        return new ResponseEntity<>(agreementList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{agreementId}")
    public ResponseEntity<Agreement> updateAgreement(@PathVariable UUID agreementId,@RequestBody AgreementDTO agreementDTO){
        Agreement updatedAgreement = agreementService.updateAgreement(agreementId, agreementDTO);
        return new ResponseEntity<>(updatedAgreement, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{agreementId}")
    public void deleteAgreement(@PathVariable UUID agreementId){
        agreementService.deleteAgreement(agreementId);
    }
}
