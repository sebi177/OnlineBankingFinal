package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.AgreementDTO;
import com.example.onlinebankingfinal.dto.AgreementFullDTO;
import com.example.onlinebankingfinal.model.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AgreementMapper {

    void updateAgreementFromDTO(AgreementDTO agreementDTO, @MappingTarget Agreement agreement);

    AgreementDTO toDto(Agreement agreement);

    List<AgreementDTO> listToDto(List<Agreement> agreementList);

    @Mapping(source = "account", target = "account.accountId")
    @Mapping(source = "product", target = "product.productId")
    Agreement toAgreement(AgreementFullDTO agreementDTO);

    @Mapping(source = "account.accountId", target = "account")
    @Mapping(source = "product.productId", target = "product")
    AgreementFullDTO toFullDto(Agreement agreement);

    @Mapping(source = "account.accountId", target = "account")
    @Mapping(source = "product.productId", target = "product")
    List<AgreementFullDTO> listToFullDto(List<Agreement> agreementList);
}
