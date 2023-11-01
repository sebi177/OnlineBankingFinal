package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    void updateClientFromDTO(ClientDTO clientDTO, @MappingTarget Client client);
}
