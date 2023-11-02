package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.ClientDTO;
import com.example.onlinebankingfinal.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    void updateClientFromDTO(ClientDTO clientDTO, @MappingTarget Client client);

    Client toClient(ClientDTO clientDTO);

    ClientDTO toDto(Client client);

    List<ClientDTO> listToDto(List<Client> clientList);
}
