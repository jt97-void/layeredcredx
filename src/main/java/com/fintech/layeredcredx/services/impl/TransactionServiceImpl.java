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
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TransactionDto getById(UUID id) {
        Transaction entity = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(TRANSACTION_NOT_FOUND + id));

        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public TransactionDto update(TransactionDto dto) {
        if (dto.getId() == null)
            throw new IllegalArgumentException("Transaction ID must not be null for update");

        Transaction existing = repository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException(TRANSACTION_NOT_FOUND + dto.getId()));

        Transaction updated = mapper.toEntity(dto);
        updated.setId(existing.getId());

        return mapper.toDto(repository.save(updated));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException(TRANSACTION_NOT_FOUND + id);

        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionDto> search(TransactionCriteriaDto criteria, Pageable pageable) {

        QTransaction q = QTransaction.transaction;
        BooleanBuilder builder = new BooleanBuilder();

//        older implementation(learned in 2023) -> cleaner and easier to use
//        if (criteria.getAccountId() != null)
//            builder.and(q.account.id.eq(criteria.getAccountId()));
//
//        if (criteria.getCardId() != null)
//            builder.and(q.card.id.eq(criteria.getCardId()));
//
//        if (criteria.getType() != null)
//            builder.and(q.type.eq(criteria.getType()));
//
//        if (criteria.getMinAmount() != null)
//            builder.and(q.amount.goe(criteria.getMinAmount()));
//
//        if (criteria.getMaxAmount() != null)
//            builder.and(q.amount.loe(criteria.getMaxAmount()));
//
//        if (criteria.getFromDate() != null)
//            builder.and(q.transactionDate.goe(criteria.getFromDate()));
//
//        if (criteria.getToDate() != null)
//            builder.and(q.transactionDate.loe(criteria.getToDate()));
//
//        if (Objects.nonNull(criteria.getDescription()) && !criteria.getDescription().isBlank())
//            builder.and(q.description.containsIgnoreCase(criteria.getDescription()));
//
//        return repository.findAll(builder, pageable).map(mapper::toDto);

        // cleaner way testing/trial :)
        addIfPresent(builder, criteria.getAccountName(), id -> builder.and(q.account.name.eq(id)));
        addIfPresent(builder, criteria.getType(), t -> builder.and(q.type.eq(t)));
        addDateRange(builder, criteria.getFromDate(), criteria.getToDate(), () -> q.transactionDate);
        addIfNotBlank(builder, criteria.getDescription(), desc -> builder.and(q.description.containsIgnoreCase(desc)));

        return repository.findAll(builder, pageable).map(mapper::toDto);
    }
}
