package com.fintech.layeredcredx.mappers;

import com.fintech.layeredcredx.dtos.TransactionDto;
import com.fintech.layeredcredx.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDto toDto(Transaction saved);
    Transaction toEntity(TransactionDto dto);
}
