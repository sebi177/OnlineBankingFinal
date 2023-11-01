package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.model.Agreement;

import java.util.List;
import java.util.UUID;

public interface AgreementService {
    Agreement createAgreement(Agreement agreement);

    Agreement getById(UUID agreementId);

    List<Agreement> getAllAgreements();

    Agreement updateAgreement(UUID agreementId, AgreementDTO agreementDTO);

    void deleteAgreement(UUID agreementId);
}
