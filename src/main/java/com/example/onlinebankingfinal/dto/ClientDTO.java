package com.example.onlinebankingfinal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ClientDTO {
    private String clientStatus;
    private String taxCode;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phone;
}
