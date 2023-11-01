package com.example.onlinebankingfinal.service.impl;

import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.mapper.ManagerMapper;
import com.example.onlinebankingfinal.model.Manager;
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
    public Manager createManager(Manager manager){
        return managerRepository.save(manager);
    }

    @Override
    public Manager getById(UUID managerId){
        return managerRepository.findById(managerId)
                .orElseThrow(() -> new EntityNotFoundException("Manager not found!"));
    }

    @Override
    public List<Manager> getAllManagers(){
        return managerRepository.findAll();
    }

    @Override
    public Manager updateManager(UUID managerId, ManagerDTO managerDTO){
        Manager existingManager = getById(managerId);
        managerMapper.updateManagerFromDto(managerDTO, existingManager);
        return managerRepository.save(existingManager);
    }

    @Override
    public void deleteManager(UUID managerId){
        Manager existingManager = managerRepository.findById(managerId)
                .orElseThrow(() -> new EntityNotFoundException("Manager not found!"));
        managerRepository.delete(existingManager);
    }
}
