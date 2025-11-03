package com.fintech.layeredcredx.services.impl;

import com.fintech.layeredcredx.dtos.TransactionCriteriaDto;
import com.fintech.layeredcredx.dtos.TransactionDto;
import com.fintech.layeredcredx.entities.QTransaction;
import com.fintech.layeredcredx.entities.Transaction;
import com.fintech.layeredcredx.mappers.TransactionMapper;
import com.fintech.layeredcredx.repositories.TransactionRepository;
import com.fintech.layeredcredx.services.TransactionService;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.fintech.layeredcredx.common.constants.ErrorMessages.TRANSACTION_NOT_FOUND;
import static com.fintech.layeredcredx.common.utils.QueryDslUtils.*;

@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

    @Override
    @Transactional
    public TransactionDto create(TransactionDto dto) {
        Transaction entity = mapper.toEntity(dto);
        Transaction saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public TransactionDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(TRANSACTION_NOT_FOUND + id));
    }

    @Override
    @Transactional
    public TransactionDto update(TransactionDto dto) {
//        In case of void use this instead
//        repository.findById(dto.getId())
//                .ifPresentOrElse(existing -> repository.save(mapper.toEntity(dto)), () -> {
//                    throw new EntityNotFoundException(TRANSACTION_NOT_FOUND + dto.getId());
//                });
        var id = dto.getId();
        if (Objects.isNull(id)) throw new IllegalArgumentException("Transaction ID must not be null for update");

        var entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(TRANSACTION_NOT_FOUND + id));

        var saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repository.findById(id)
                .ifPresentOrElse(repository::delete, () -> {
                    throw new EntityNotFoundException(TRANSACTION_NOT_FOUND + id);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionDto> search(TransactionCriteriaDto criteria, Pageable pageable) {

        QTransaction q = QTransaction.transaction;
        BooleanBuilder builder = new BooleanBuilder();

        addIfPresent(builder, criteria.getAccountName(), id -> builder.and(q.account.name.eq(id)));
        addIfPresent(builder, criteria.getType(), t -> builder.and(q.type.eq(t)));
        addDateRange(builder, criteria.getFromDate(), criteria.getToDate(), () -> q.transactionDate);
        addIfNotBlank(builder, criteria.getDescription(), desc -> builder.and(q.description.containsIgnoreCase(desc)));

        return repository.findAll(builder, pageable).map(mapper::toDto);
    }
}
