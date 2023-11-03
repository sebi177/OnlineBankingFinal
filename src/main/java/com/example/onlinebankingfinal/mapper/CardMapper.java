package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.dto.CardFullDTO;
import com.example.onlinebankingfinal.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper {

    void updateCardFromDTO(CardDTO cardDTO, @MappingTarget Card card);

    Card toCard(CardDTO cardDTO);

    CardDTO toDto(Card card);

    List<CardDTO> listToDto(List<Card> cardList);

    @Mapping(source = "account", target = "account.accountId")
    @Mapping(source = "client", target = "client.clientId")
    void updateCard(CardFullDTO cardDTO, @MappingTarget Card card);

    @Mapping(source = "account", target = "account.accountId")
    @Mapping(source = "client", target = "client.clientId")
    Card toCard(CardFullDTO cardDTO);

    @Mapping(source = "account.accountId", target = "account")
    @Mapping(source = "client.clientId", target = "client")
    CardFullDTO toFullDto(Card card);

    @Mapping(source = "account.accountId", target = "account")
    @Mapping(source = "client.clientId", target = "client")
    List<CardFullDTO> listToFullDto(List<Card> cardList);
}
