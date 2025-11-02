package com.fintech.layeredcredx.services;

import com.fintech.layeredcredx.dtos.TransactionCriteriaDto;
import com.fintech.layeredcredx.dtos.TransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionDto create(TransactionDto dto);

    Page<TransactionDto> getAll(Pageable pageable);

    List<TransactionDto> getAll();

    TransactionDto getById(UUID id);

    TransactionDto update(TransactionDto dto);

    void delete(UUID id);

    Page<TransactionDto> search(TransactionCriteriaDto criteria, Pageable pageable);

}
