package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.mapper.ManagerMapper;
import com.example.onlinebankingfinal.model.Manager;
import com.example.onlinebankingfinal.model.enums.ManagerStatus;
import com.example.onlinebankingfinal.repository.ManagerRepository;
import com.example.onlinebankingfinal.service.ManagerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Override
    public ManagerDTO createManager(Manager manager){
        return managerMapper.toDto(managerRepository.save(manager));
    }

    @Override
    public Manager getById(UUID managerId){
        return managerRepository.findById(managerId)
                .orElseThrow(() -> new EntityNotFoundException("Manager not found!"));
    }

    @Override
    public ManagerDTO getDtoById(UUID managerId){
        return managerMapper.toDto(getById(managerId));
    }

    @Override
    public List<ManagerDTO> getAllManagers(){
        return managerMapper.listToDto(managerRepository.findAll());
    }

    @Override
    public ManagerDTO updateManager(UUID managerId, ManagerDTO managerDTO){
        Manager existingManager = getById(managerId);
        managerMapper.updateManagerFromDto(managerDTO, existingManager);
        return managerMapper.toDto(managerRepository.save(existingManager));
    }

    @Override
    public ManagerDTO deleteManager(UUID managerId){
        Manager existingManager = getById(managerId);
        existingManager.setManagerStatus(ManagerStatus.DELETED);
        managerRepository.save(existingManager);
        return managerMapper.toDto(existingManager);
    }
}
