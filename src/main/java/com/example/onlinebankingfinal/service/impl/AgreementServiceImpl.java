package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.mapper.AgreementMapper;
import com.example.onlinebankingfinal.model.Agreement;
import com.example.onlinebankingfinal.repository.AgreementRepository;
import com.example.onlinebankingfinal.service.AgreementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;

    @Override
    public Agreement createAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    @Override
    public Agreement getById(UUID agreementId) {
        return agreementRepository.findById(agreementId)
                .orElseThrow(() -> new EntityNotFoundException("Agreement not found!"));
    }

    @Override
    public List<Agreement> getAllAgreements() {
        return agreementRepository.findAll();
    }

    @Override
    public Agreement updateAgreement(UUID agreementId, AgreementDTO agreementDTO){
        Agreement existingAgreement = getById(agreementId);
        agreementMapper.updateAgreementFromDTO(agreementDTO, existingAgreement);
        return agreementRepository.save(existingAgreement);
    }

    @Override
    public void deleteAgreement(UUID agreementId){
        Agreement existingAgreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new EntityNotFoundException("Agreement not found!"));
        agreementRepository.delete(existingAgreement);
    }

}
