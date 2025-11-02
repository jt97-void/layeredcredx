package com.fintech.layeredcredx.mappers;

import com.fintech.layeredcredx.dtos.CardDto;
import com.fintech.layeredcredx.entities.Card;
import org.mapstruct.factory.Mappers;

public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    CardDto toDto(Card saved);
    Card toEntity(CardDto dto);
}
