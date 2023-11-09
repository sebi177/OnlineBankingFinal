package com.example.onlinebankingfinal.dto;

import lombok.Data;

@Data
public class TransactionStatisticsDTO {
    private double totalIncoming;
    private double totalOutgoing;
    private double percentageIncoming;
    private double percentageOutgoing;

    public TransactionStatisticsDTO(double totalIncoming, double totalOutgoing, double percentageIncoming, double percentageOutgoing) {
        this.totalIncoming = totalIncoming;
        this.totalOutgoing = totalOutgoing;
        this.percentageIncoming = percentageIncoming;
        this.percentageOutgoing = percentageOutgoing;
    }
}
