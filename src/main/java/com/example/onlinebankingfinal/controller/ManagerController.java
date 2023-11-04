package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ClientFullDTO;
import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.Manager;
import com.example.onlinebankingfinal.service.ClientService;
import com.example.onlinebankingfinal.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ManagerDTO> createManager(@RequestBody Manager manager) {
        ManagerDTO createdManager = managerService.createManager(manager);
        return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
    }

    @GetMapping("/find/{managerId}")
    public ResponseEntity<ManagerDTO> getManagerById(@PathVariable UUID managerId) {
        ManagerDTO foundedManager = managerService.getDtoById(managerId);
        return new ResponseEntity<>(foundedManager, HttpStatus.FOUND);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ManagerDTO>> getAllManagers(){
        List<ManagerDTO> managerList = managerService.getAllManagers();
        return new ResponseEntity<>(managerList, HttpStatus.FOUND);
    }

    @PostMapping("/update/{managerId}")
    public ResponseEntity<ManagerDTO> updateManager(@PathVariable UUID managerId, @RequestBody ManagerDTO managerDTO){
        ManagerDTO updatedManager = managerService.updateManager(managerId, managerDTO);
        return new ResponseEntity<>(updatedManager, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{managerId}")
    public void deleteManager(@PathVariable UUID managerId){
        managerService.deleteManager(managerId);
    }

//    @PostMapping("/{managerId}/genClient")
//    public ResponseEntity<Client> generateClient(UUID managerId){
//        Client client = clientService.generateRandomClient(managerId);
//        return new ResponseEntity<>(client, HttpStatus.CREATED);
//    }
}
