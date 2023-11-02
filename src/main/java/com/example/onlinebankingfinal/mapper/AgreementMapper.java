package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.model.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AgreementMapper {

    void updateAgreementFromDTO(AgreementDTO agreementDTO, @MappingTarget Agreement agreement);

    AgreementDTO toDto(Agreement agreement);

    Agreement toAgreement(AgreementDTO agreementDTO);

    List<AgreementDTO> listToDto(List<Agreement> agreementList);
}
