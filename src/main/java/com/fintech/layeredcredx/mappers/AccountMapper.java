package com.fintech.layeredcredx.mappers;

import com.fintech.layeredcredx.dtos.AccountDto;
import com.fintech.layeredcredx.dtos.CardDto;
import com.fintech.layeredcredx.entities.Account;
import com.fintech.layeredcredx.entities.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto toDto(Account account);
    Account toEntity(AccountDto dto);

    List<AccountDto> toDtoList(List<Account> entities);
}
