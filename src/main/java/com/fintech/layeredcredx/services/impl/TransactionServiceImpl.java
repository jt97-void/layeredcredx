package com.fintech.layeredcredx.services.impl;

import com.fintech.layeredcredx.dtos.TransactionDto;
import com.fintech.layeredcredx.services.TransactionService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public TransactionDto createTransaction(TransactionDto dto) {
        return null;
    }

    @Override
    public Page<TransactionDto> getAllTransactionDto() {
        return null;
    }

    @Override
    public TransactionDto getTransactionById(UUID id) {
        return null;
    }

    @Override
    public TransactionDto updateTransaction(TransactionDto dto) {
        return null;
    }

    @Override
    public void deleteTransaction(UUID id) {

    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return List.of();
    }
}
