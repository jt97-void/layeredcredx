package com.fintech.layeredcredx.entities;

import com.fintech.layeredcredx.common.enums.TransactionType;
import com.querydsl.core.annotations.QueryInit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseAuditableEntity {

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(length = 100)
    private String reference;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private boolean linked;

    @QueryInit("*")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @QueryInit("*")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @PrePersist
    protected void onCreateTransaction() {
        if (Objects.isNull(transactionDate)) {
            transactionDate = LocalDateTime.now();
        }
    }

}
