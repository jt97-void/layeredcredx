package com.fintech.layeredcredx.mappers;

import com.fintech.layeredcredx.dtos.TransactionDto;
import com.fintech.layeredcredx.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDto(Transaction saved);
    Transaction toEntity(TransactionDto dto);

    List<TransactionDto> toDtoList(List<Transaction> entities);
}
