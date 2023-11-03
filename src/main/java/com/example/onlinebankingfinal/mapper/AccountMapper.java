package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.AccountDTO;
import com.example.onlinebankingfinal.dto.AccountFullDTO;
import com.example.onlinebankingfinal.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    void updateAccountFromDTO(AccountDTO accountDTO, @MappingTarget Account account);

    Account toAccount(AccountDTO accountDTO);

    AccountDTO toDTO(Account account);

    List<AccountDTO> toDTO(List<Account> accountList);

    @Mapping(source = "client", target = "client.clientId")
    @Mapping(source = "card", target = "card.cardId")
    void updateAccount(AccountFullDTO accountFullDTO, @MappingTarget Account account);

    @Mapping(source = "client", target = "client.clientId")
    @Mapping(source = "card", target = "card.cardId")
    Account toAccount(AccountFullDTO accountFullDTO);

    @Mapping(source = "client.clientId", target = "client")
    @Mapping(source = "card.cardId", target = "card")
    AccountFullDTO toFullDTO(Account account);

    @Mapping(source = "client.clientId", target = "client")
    @Mapping(source = "card.cardId", target = "card")
    List<AccountFullDTO> toFullDTO(List<Account> accountList);

}
