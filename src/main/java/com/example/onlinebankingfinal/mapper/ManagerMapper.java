package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.model.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ManagerMapper {

    void updateManagerFromDto(ManagerDTO managerDTO, @MappingTarget Manager manager);
}
