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

    CardDTO toDto(Card card);

    List<CardDTO> listToDto(List<Card> cardList);

    @Mapping(source = "account.accountId", target = "account")
    @Mapping(source = "client.clientId", target = "client")
    @Mapping(source = "card.expirationDate", target = "expirationDate")
    CardFullDTO toFullDto(Card card);

    @Mapping(source = "account.accountId", target = "account")
    @Mapping(source = "client.clientId", target = "client")
    @Mapping(source = "card.expirationDate", target = "expirationDate")
    List<CardFullDTO> listToFullDto(List<Card> cardList);
}
