package com.example.onlinebankingfinal.mapper;

import com.example.onlinebankingfinal.dto.CardDTO;
import com.example.onlinebankingfinal.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper {

    void updateCardFromDTO(CardDTO cardDTO, @MappingTarget Card card);

    Card toCard(CardDTO cardDTO);

    CardDTO toDto(Card card);

    List<CardDTO> listToDto(List<Card> cardList);
}
