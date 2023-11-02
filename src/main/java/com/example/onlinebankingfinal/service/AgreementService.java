package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.model.Agreement;

import java.util.List;
import java.util.UUID;

public interface AgreementService {

    AgreementDTO createAgreement(Agreement agreement);

    AgreementDTO getDtoById(UUID agreementId);

    Agreement getById(UUID agreementId);

    List<AgreementDTO> getAllAgreements();

    AgreementDTO updateAgreement(UUID agreementId, AgreementDTO agreementDTO);

    void deleteAgreement(UUID agreementId);
}
