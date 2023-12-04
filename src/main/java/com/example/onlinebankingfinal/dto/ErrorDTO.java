package com.example.onlinebankingfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    Integer code;
    String message;
}
