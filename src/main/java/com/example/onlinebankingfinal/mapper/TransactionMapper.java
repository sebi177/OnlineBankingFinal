package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.TransactionDTO;
import com.example.onlinebankingfinal.dto.TransactionFullDTO;
import com.example.onlinebankingfinal.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {

    TransactionDTO toDto(Transaction transaction);

    List<TransactionDTO> listToDto(List<Transaction> transactionList);

    @Mapping(source = "debitAccount", target = "debitAccount.accountId")
    @Mapping(source = "creditAccount", target = "creditAccount.accountId")
    Transaction toTransaction(TransactionFullDTO transactionDTO);

    @Mapping(source = "debitAccount.accountId", target = "debitAccount")
    @Mapping(source = "creditAccount.accountId", target = "creditAccount")
    TransactionFullDTO toFullDto(Transaction transaction);

    @Mapping(source = "debitAccount.accountId", target = "debitAccount")
    @Mapping(source = "creditAccount.accountId", target = "creditAccount")
    List<TransactionFullDTO> listToFullDto(List<Transaction> transactionList);

}
