package com.example.onlinebankingfinal.service;

import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.model.Manager;

import java.util.List;
import java.util.UUID;

public interface ManagerService {
    Manager createManager(Manager manager);

    Manager getById(UUID managerId);

    List<Manager> getAllManagers();

    Manager updateManager(UUID managerId, ManagerDTO managerDTO);

    void deleteManager(UUID managerId);
}
