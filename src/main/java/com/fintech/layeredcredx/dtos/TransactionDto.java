package com.fintech.layeredcredx.dtos;

import com.fintech.layeredcredx.common.enums.TransactionType;
import com.fintech.layeredcredx.entities.Account;
import com.fintech.layeredcredx.entities.Card;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransactionDto {

    private UUID id;

    private UUID accountId;

    private UUID cardId;

    private BigDecimal amount;

    private TransactionType type;

    private String description;

    private String reference;

    private LocalDateTime transactionDate;

    private boolean linked;

}
