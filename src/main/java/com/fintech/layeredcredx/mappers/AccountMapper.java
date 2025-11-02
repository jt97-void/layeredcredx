package com.fintech.layeredcredx.mappers;

import com.fintech.layeredcredx.dtos.AccountDto;
import com.fintech.layeredcredx.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDto(Account account);
    Account toEntity(AccountDto dto);
}
