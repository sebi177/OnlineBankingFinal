package com.example.onlinebankingfinal.dto;

import lombok.Data;

@Data
public class ClientFullDTO {
    private String clientStatus;
    private String taxCode;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
    private String manager;
}
