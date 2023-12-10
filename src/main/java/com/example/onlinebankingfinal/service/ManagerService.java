package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.model.Manager;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    ManagerDTO createManager(Manager manager);

    Manager getById(UUID managerId);

    ManagerDTO getDtoById(UUID managerId);

    List<ManagerDTO> getAllManagers();

    ManagerDTO updateManager(UUID managerId, ManagerDTO managerDTO);

    ManagerDTO deleteManager(UUID managerId);
}
