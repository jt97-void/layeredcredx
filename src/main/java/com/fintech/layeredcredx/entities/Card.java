package com.fintech.layeredcredx.entities;

import com.fintech.layeredcredx.common.enums.CardNetwork;
import com.fintech.layeredcredx.common.enums.CardType;
import com.querydsl.core.annotations.QueryInit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Card extends BaseAuditableEntity {

    @Column(nullable = false, length = 50)
    private String cardNumberMasked;

    @Column(nullable = false, length = 100)
    private String cardHolderName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CardType type;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(length = 20)
    private CardNetwork network;

    @QueryInit("*")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
