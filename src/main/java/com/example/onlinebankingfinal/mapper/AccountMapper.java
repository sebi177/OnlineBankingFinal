package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    void updateAccountFromDTO(AccountDTO accountDTO,@MappingTarget Account account);

    Account toAccount(AccountDTO accountDTO);

    AccountDTO toDTO(Account account);

    List<AccountDTO> toDTO(List<Account> accountList);
}
