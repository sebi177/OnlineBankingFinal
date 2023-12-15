package com.example.onlinebankingfinal.controller;

import com.example.onlinebankingfinal.dto.ClientFullDTO;
import com.example.onlinebankingfinal.dto.ManagerDTO;
import com.example.onlinebankingfinal.dto.ProductFullDTO;
import com.example.onlinebankingfinal.model.Client;
import com.example.onlinebankingfinal.model.Manager;
import com.example.onlinebankingfinal.model.Product;
import com.example.onlinebankingfinal.service.ClientService;
import com.example.onlinebankingfinal.service.ManagerService;
import com.example.onlinebankingfinal.service.ProductService;
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
    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDTO createManager(@RequestBody ManagerDTO manager) {
        return managerService.createManager(manager);
    }

    @GetMapping("/find/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDTO getManagerById(@PathVariable UUID managerId) {
        return managerService.getDtoById(managerId);
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerDTO> getAllManagers(){
        return managerService.getAllManagers();
    }

    @PostMapping("/update/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDTO updateManager(@PathVariable UUID managerId, @RequestBody ManagerDTO managerDTO){
        return managerService.updateManager(managerId, managerDTO);
    }

    @PostMapping("/delete/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDTO deleteManager(@PathVariable UUID managerId){
        return managerService.deleteManager(managerId);
    }

    @PostMapping("/{managerId}/createClient")
    @ResponseStatus(HttpStatus.OK)
    public ClientFullDTO createClient(@PathVariable UUID managerId,@RequestBody Client client){
        return clientService.createClientByManager(managerId, client);
    }

    @PostMapping("/{managerId}/createProduct")
    @ResponseStatus(HttpStatus.OK)
    public ProductFullDTO createProduct(@PathVariable UUID managerId, @RequestBody Product product){
        return productService.createProductByManager(managerId, product);
    }
}
