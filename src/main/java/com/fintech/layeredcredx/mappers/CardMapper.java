package com.fintech.layeredcredx.mappers;

import com.fintech.layeredcredx.dtos.CardDto;
import com.fintech.layeredcredx.entities.Card;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardDto toDto(Card saved);
    Card toEntity(CardDto dto);

    List<CardDto> toDtoList(List<Card> entities);
}
