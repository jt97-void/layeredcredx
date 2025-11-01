package com.fintech.layeredcredx.services;

import com.fintech.layeredcredx.dtos.TransactionDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionDto createTransaction(TransactionDto dto);

    Page<TransactionDto> getAllTransactionDto();

    TransactionDto getTransactionById(UUID id);

    TransactionDto updateTransaction(TransactionDto dto);

    void deleteTransaction(UUID id);

    List<TransactionDto> getAllTransactions();
}
