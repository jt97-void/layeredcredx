package com.fintech.layeredcredx.dtos;

import com.fintech.layeredcredx.common.enums.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionCriteriaDto {
    private String accountName;
    private String cardNumber;
    private TransactionType type;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String description;
}
