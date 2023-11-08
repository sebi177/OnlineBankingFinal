package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.dto.AgreementFullDTO;
import com.example.onlinebankingfinal.mapper.AgreementMapper;
import com.example.onlinebankingfinal.model.Account;
import com.example.onlinebankingfinal.model.Agreement;
import com.example.onlinebankingfinal.repository.AgreementRepository;
import com.example.onlinebankingfinal.service.AccountService;
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
    private final AccountService accountService;

    @Override
    public AgreementDTO createAgreement(Agreement agreement) {
        return agreementMapper.toDto(agreementRepository.save(agreement));
    }

    @Override
    public AgreementDTO getDtoById(UUID agreementId) {
        return agreementMapper.toDto(getById(agreementId));
    }

    @Override
    public Agreement getById(UUID agreementId) {
        return agreementRepository.findById(agreementId)
                .orElseThrow(() -> new EntityNotFoundException("Agreement not found!"));
    }

    @Override
    public List<AgreementDTO> getAllAgreements() {
        return agreementMapper.listToDto(agreementRepository.findAll());
    }

    @Override
    public AgreementDTO updateAgreement(UUID agreementId, AgreementDTO agreementDTO){
        Agreement existingAgreement = getById(agreementId);
        agreementMapper.updateAgreementFromDTO(agreementDTO, existingAgreement);
        return agreementMapper.toDto(agreementRepository.save(existingAgreement));
    }

    @Override
    public void deleteAgreement(UUID agreementId){
        Agreement existingAgreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new EntityNotFoundException("Agreement not found!"));
        agreementRepository.delete(existingAgreement);
    }

    @Override
    public AgreementFullDTO createAgreementByAccount(UUID accountId, AgreementFullDTO agreement){
        agreement.setAccount(String.valueOf(accountId));
        Agreement thisAgreement = agreementMapper.toAgreement(agreement);
        agreementRepository.save(thisAgreement);
        return agreement;
    }

}
