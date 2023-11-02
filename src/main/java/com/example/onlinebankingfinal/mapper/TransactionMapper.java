package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {

    void updateTransactionFromDTO(TransactionDTO transactionDTO, @MappingTarget Transaction transaction);

    Transaction toTransaction(TransactionDTO transactionDTO);

    TransactionDTO toDto(Transaction transaction);

    List<TransactionDTO> listToDto(List<Transaction> transactionList);
}
