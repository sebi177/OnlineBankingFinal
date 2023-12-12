package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.dto.ClientFullDTO;
import com.example.onlinebankingfinal.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    void updateClientFromDTO(ClientDTO clientDTO, @MappingTarget Client client);

    ClientDTO toDto(Client client);

    List<ClientDTO> listToDto(List<Client> clientList);

    @Mapping(source = "manager.managerId",target = "manager")
    ClientFullDTO toFullDto(Client client);

    @Mapping(source = "manager.managerId",target = "manager")
    List<ClientFullDTO> listToFullDto(List<Client> clientList);
}
